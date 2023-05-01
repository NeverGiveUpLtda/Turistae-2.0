package com.api.turistae.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.api.turistae.utils.Criptografia;
import com.api.turistae.utils.DataUtils;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    // Atributos
    private UsuarioService usuarioService;
    private final Logger logger;
    private static final String MASCARA_DATA = "yyyy-MM-dd-HH-mm-ss";

    // Construtor
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        this.logger = LoggerFactory.getLogger(UsuarioController.class);
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
        logger.info("Get usuário id: {}", id);
        return usuarioService.getById(id);
    }

    // HttpPost
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long postUsuario(@RequestBody UsuarioDTO usuarioDTO) {

        String senha = usuarioDTO.getSenha();

        try {
            usuarioDTO.setSenha(Criptografia.criptografarSenha(senha));
        } catch (CriptografiaException e) {
            throw new RegraNegocioException(e.getMessage());
        }

        usuarioDTO.setDataCriacao(DataUtils.getDataAtualComMascara(MASCARA_DATA));
        usuarioDTO.setDataEdicao(DataUtils.getDataAtualComMascara(MASCARA_DATA));

        logger.info("Post usuário: {}", usuarioDTO);

        return usuarioService.post(usuarioDTO);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public DadosUsuarioDTO login(@RequestBody UsuarioDTO usuarioDTO) {
        String senha;
        if (usuarioDTO.getSenha() != null) {
            senha = usuarioDTO.getSenha();
        } else
            throw new RegraNegocioException("Usuário ou senha inválidos.");

        try {
            usuarioDTO.setSenha(Criptografia.criptografarSenha(senha));
        } catch (CriptografiaException e) {
            throw new RegraNegocioException(e.getMessage());
        }

        if (usuarioDTO.getNomeUsuario() != null && !usuarioDTO.getNomeUsuario().isEmpty()) {
            usuarioDTO.setEmail("");

            return usuarioService.login(usuarioDTO.getNomeUsuario(), usuarioDTO.getSenha());
        } else if (usuarioDTO.getEmail() != null && !usuarioDTO.getEmail().isEmpty()) {
            usuarioDTO.setNomeUsuario("senha");

            return usuarioService.login(usuarioDTO.getEmail(), usuarioDTO.getSenha());
        } else
            throw new RegraNegocioException("Usuário ou senha inválidos.");
    }

    // HttpPut
    @PutMapping("{id}")
    public void putUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {

        String senha = usuarioDTO.getSenha();

        try {
            usuarioDTO.setSenha(Criptografia.criptografarSenha(senha));
        } catch (CriptografiaException e) {
            throw new RegraNegocioException(e.getMessage());
        }

        usuarioDTO.setDataEdicao(DataUtils.getDataAtualComMascara(MASCARA_DATA));

        logger.info("Put usuário id {}: {}", id, usuarioDTO);

        usuarioService.put(id, usuarioDTO);
    }

    // HttpDelete
    @DeleteMapping("{id}")
    public void deleteUsuario(@PathVariable Long id) {

        logger.info("Delete usuário id {}", id);

        usuarioService.delete(id);
    }

}
