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
import com.api.turistae.services.UsuarioService;
import com.api.turistae.services.UsuarioServiceImpl;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    // Atributos
    private UsuarioService usuarioService;
    private final Logger logger;

    // Construtor
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        this.logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);
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
        logger.info("Post usuário: {}", usuarioDTO);
        return usuarioService.post(usuarioDTO);
    }

    // HttpPut
    @PutMapping("{id}")
    public void putUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        logger.info("Put usuário id {}: {}", id, usuarioDTO);
        usuarioService.put(id, usuarioDTO);
    }

    // HttpDelete
    @DeleteMapping("{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
    }

}
