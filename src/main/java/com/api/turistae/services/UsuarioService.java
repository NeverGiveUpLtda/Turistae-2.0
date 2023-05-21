package com.api.turistae.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.api.turistae.dtos.UsuarioDTO;
import com.api.turistae.models.Usuario;

public interface UsuarioService {

    UsuarioDTO obterUsuarioPorId(Integer id);

    Usuario salvar(UsuarioDTO dto);

    List<UsuarioDTO> obterUsuarios();

    UserDetails autenticar(Usuario usuario);

}
