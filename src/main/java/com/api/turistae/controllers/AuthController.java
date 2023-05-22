package com.api.turistae.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.api.turistae.dtos.AuthDTO;
import com.api.turistae.dtos.TokenDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Usuario;
import com.api.turistae.services.UsuarioService;
import com.api.turistae.services.security.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // Atributos
    private UsuarioService usuarioService;
    private JwtService jwtService;
    private final Logger logger;

    // Construtor
    public AuthController(UsuarioService usuarioService, JwtService jwtService) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
        this.logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Auth Controller iniciado.");
    }

    // HttpPost
    @PostMapping()
    public TokenDTO autenticar(@RequestBody AuthDTO autenticacao) {
        logger.info("Iniciada autenticação: {}", autenticacao);
        try {
            Usuario usuario = new Usuario(0, "", autenticacao.getUsername(), autenticacao.getSenha(), "", null, null, null);
            UserDetails usuarioAutenticado = usuarioService.auth(usuario);
            logger.info("Usuário autenticado: {}", usuarioAutenticado);
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getEmail(), token);
        } catch (UsernameNotFoundException | RegraNegocioException ex) {
            logger.error("Autenticação inválida: {}", autenticacao);
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }
}
