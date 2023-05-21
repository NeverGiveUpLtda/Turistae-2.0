package com.api.turistae.services;

import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.turistae.dtos.UsuarioDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Usuario;
import com.api.turistae.repositorys.UsuarioRepository;
import com.api.turistae.utils.DataUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Usuario salvar(UsuarioDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setNome(dto.getNome());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setDataCriacao(DataUtils.getDataAtualComMascara());
        usuario.setDataEdicao(DataUtils.getDataAtualComMascara());
        usuario.setPerfil(dto.getPerfil());

        return usuarioRepository.save(usuario);

    }

    @Override
    public UsuarioDTO obterUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id).map(u -> {
            return UsuarioDTO
                    .builder()
                    .nome(u.getNome())
                    .email(u.getEmail())
                    .perfil(u.getPerfil())
                    .build();
        })
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));
    }

    @Override
    public List<UsuarioDTO> obterUsuarios() {
        List<UsuarioDTO> dados = usuarioRepository.findAll().stream().map(u -> {
            return UsuarioDTO
                    .builder()
                    .email(u.getEmail())
                    .nome(u.getNome())
                    .perfil(u.getPerfil())
                    .build();
        }).toList();

        return dados;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByEmail(username);

        String[] roles = usuario.getPerfil() == "Administrador" ? new String[] { "ADMIN", "USER" }
                : new String[] { "USER" };
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    @Override
    public UserDetails autenticar(Usuario usuario) {
        UserDetails user = loadUserByUsername(usuario.getEmail());
        boolean senhaOK = passwordEncoder.matches(usuario.getSenha(), user.getPassword());
        if (senhaOK) {
            return user;
        }
        throw new RegraNegocioException("Senha inválida");
    }

}
