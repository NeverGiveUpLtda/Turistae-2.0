package com.api.turistae.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.turistae.dtos.DadosUsuarioDTO;
import com.api.turistae.dtos.UsuarioDTO;
import com.api.turistae.exceptions.CriptografiaException;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.services.UsuarioService;
import com.api.turistae.utils.CriptografiaUtils;
import com.api.turistae.utils.DataUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    // Atributos
    private UsuarioService usuarioService;
    private final Logger logger;

    // Construtor
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        this.logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Usuário Controller iniciado.");
    }

    // HttpGet
    @GetMapping
    public List<DadosUsuarioDTO> getUsuarios() {
        logger.info("Get todos usuários.");
        return usuarioService.getAll();
    }

    @GetMapping("{id}")
    public DadosUsuarioDTO getUsuarioPorId(@PathVariable Long id) {
        logger.info("Get usuário.");
        return usuarioService.getById(id);
    }

    // HttpPost
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long postUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {

        //TODO
        String senha = usuarioDTO.getSenha();

        try {
            usuarioDTO.setSenha(CriptografiaUtils.criptografarSenha(senha));
        } catch (CriptografiaException e) {
            throw new RegraNegocioException(e.getMessage());
        }

        usuarioDTO.setDataCriacao(DataUtils.getDataAtualComMascara());
        usuarioDTO.setDataEdicao(DataUtils.getDataAtualComMascara());

        logger.info("Post usuário.");

        //  Se usuario ou email já exisirem na tabela, retornar erro
        // Retorno do cadastro
        Long id = 0l;
        try {
            id = usuarioService.post(usuarioDTO);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("nome_usuario_UNIQUE")) {
                throw new RegraNegocioException("Nome de usuário indisponível.");
            } else if (e.getMessage().contains("email_UNIQUE")) {
                throw new RegraNegocioException("Endereço de email já cadastrado.");
            } else {
                throw new RegraNegocioException("Erro ao cadastrar usuário.");
            }
        }

        return id;
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public DadosUsuarioDTO login(@RequestBody UsuarioDTO usuarioDTO) {

        //TODO
        String senha;
        if (usuarioDTO.getNomeUsuario() != null && usuarioDTO.getSenha() != null) {
            senha = usuarioDTO.getSenha();
        } else
            throw new RegraNegocioException("Usuário ou senha inválidos.");

        try {
            usuarioDTO.setSenha(CriptografiaUtils.criptografarSenha(senha));
        } catch (CriptografiaException e) {
            throw new RegraNegocioException(e.getMessage());
        }

        if (usuarioDTO.getNomeUsuario() != null && !usuarioDTO.getNomeUsuario().isEmpty()) {

            return usuarioService.login(usuarioDTO.getNomeUsuario(), usuarioDTO.getSenha());

        } else
            throw new RegraNegocioException("Usuário ou senha inválidos.");
    }

    // HttpPut
    @PutMapping("{id}")
    public void putUsuario(@PathVariable Long id, @Valid @RequestBody UsuarioDTO usuarioDTO) {

        //TODO
        String senha = usuarioDTO.getSenha();

        try {
            usuarioDTO.setSenha(CriptografiaUtils.criptografarSenha(senha));
        } catch (CriptografiaException e) {
            throw new RegraNegocioException(e.getMessage());
        }

        usuarioDTO.setDataEdicao(DataUtils.getDataAtualComMascara());

        logger.info("Put usuário.");

        //  Se usuario ou email já exisirem na tabela, retornar erro
        try {
            usuarioService.put(id, usuarioDTO);
        } catch (DataIntegrityViolationException e) {
            if (e.getMessage().contains("nome_usuario_UNIQUE")) {
                throw new RegraNegocioException("Nome de usuário indisponível.");
            } else if (e.getMessage().contains("email_UNIQUE")) {
                throw new RegraNegocioException("Endereço de email indisponível.");
            } else {
                throw new RegraNegocioException("Erro ao atualizar usuário.");
            }
        }

    }

    // HttpDelete
    @DeleteMapping("{id}")
    public void deleteUsuario(@PathVariable Long id) {

        logger.info("Delete usuário.");

        usuarioService.delete(id);
    }

}
