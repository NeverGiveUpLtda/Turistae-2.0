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

import com.api.turistae.dtos.DadosTuristaDTO;
import com.api.turistae.dtos.TuristaDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.services.TuristaService;
import com.api.turistae.utils.DataUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/turista")
public class TuristaController {

    // Atributos
    private TuristaService turistaService;
    private final Logger logger;

    // Construtor
    public TuristaController(TuristaService turistaService) {
        this.turistaService = turistaService;
        this.logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Turista Controller iniciado.");
    }

    // HttpGet
    @GetMapping
    public List<DadosTuristaDTO> getTuristas() {
        logger.info("Get todos turistas.");
        return turistaService.getAll();
    }

    @GetMapping("{id}")
    public DadosTuristaDTO getTuristaPorId(@PathVariable Long id) {
        logger.info("Get turista id: {}", id);
        return turistaService.getById(id);
    }

    // HttpPost
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long postTurista(@Valid @RequestBody TuristaDTO turistaDTO) {

        logger.info("Post turista: {}", turistaDTO);

        //  Se turista ou email já exisirem na tabela, retornar erro
        // Retorno do cadastro
        Long id = 0l;

        //TODO
         try {
             id = turistaService.post(turistaDTO);
         } catch (DataIntegrityViolationException e) {
        //     if (e.getMessage().contains("nome_turista_UNIQUE")) {
        //         throw new RegraNegocioException("Nome de usuário indisponível.");
        //     } else if (e.getMessage().contains("email_UNIQUE")) {
        //         throw new RegraNegocioException("Endereço de email já cadastrado.");
        //     } else {
                 throw new RegraNegocioException("Erro ao cadastrar turista.");
        //     }
         }

        return id;
    }

    //TODO
    // @PostMapping("/login")
    // @ResponseStatus(HttpStatus.OK)
    // public DadosturistaDTO login(@RequestBody TuristaDTO turistaDTO) {

    //     //TODO
    //     String senha;
    //     if (turistaDTO.getNometurista() != null && turistaDTO.getSenha() != null) {
    //         senha = turistaDTO.getSenha();
    //     } else
    //         throw new RegraNegocioException("Usuário ou senha inválidos.");

    //     try {
    //         turistaDTO.setSenha(CriptografiaUtils.criptografarSenha(senha));
    //     } catch (CriptografiaException e) {
    //         throw new RegraNegocioException(e.getMessage());
    //     }

    //     if (turistaDTO.getNometurista() != null && !turistaDTO.getNometurista().isEmpty()) {

    //         return turistaService.login(turistaDTO.getNometurista(), turistaDTO.getSenha());

    //     } else
    //         throw new RegraNegocioException("Usuário ou senha inválidos.");
    // }

    // HttpPut
    @PutMapping("{id}")
    public void putTurista(@PathVariable Long id, @Valid @RequestBody TuristaDTO turistaDTO) {

        //TODO
        turistaDTO.setDataEdicao(DataUtils.getDataAtualComMascara());

        logger.info("Put turista id {}: {}", id, turistaDTO);

        //  Se turista ou email já exisirem na tabela, retornar erro
        try {
            turistaService.put(id, turistaDTO);
        } catch (DataIntegrityViolationException e) {
            // if (e.getMessage().contains("nome_turista_UNIQUE")) {
            //     throw new RegraNegocioException("Nome de usuário indisponível.");
            // } else if (e.getMessage().contains("email_UNIQUE")) {
            //     throw new RegraNegocioException("Endereço de email indisponível.");
            // } else {
                throw new RegraNegocioException("Erro ao atualizar turista.");
            // }
        }

    }

    // HttpDelete
    @DeleteMapping("{id}")
    public void deleteTurista(@PathVariable Long id) {

        logger.info("Delete turista id {}", id);

        turistaService.delete(id);
    }

}
