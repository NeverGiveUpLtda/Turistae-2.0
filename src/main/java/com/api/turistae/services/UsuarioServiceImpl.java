package com.api.turistae.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.api.turistae.dtos.TuristaDTO;
import com.api.turistae.dtos.UsuarioDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Usuario;
import com.api.turistae.repositories.UsuarioRepository;
import com.api.turistae.utils.DataUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService, UserDetailsService {
    /**
     *
     */
    private static final String NÃO_ENCONTRADO = "Usuário não encontrado.";
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Integer post(UsuarioDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setNome(dto.getNome());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setDataCriacao(DataUtils.getDataAtualComMascara());
        usuario.setDataEdicao(DataUtils.getDataAtualComMascara());
        usuario.setPerfil(dto.getPerfil());

        Usuario usuarioGerado = usuarioRepository.save(usuario);

        return usuarioGerado.getId();

    }

    @Override
    public List<UsuarioDTO> getAll() {
        return usuarioRepository.findAll().stream().map((Usuario u) -> UsuarioDTO
                .builder()
                .email(u.getEmail())
                .nome(u.getNome())
                .senha(u.getSenha())
                .dataCriacao(u.getDataCriacao())
                .dataEdicao(u.getDataEdicao())
                .perfil(u.getPerfil())
                .build())
                .collect(Collectors.toList());

    }

    @Override
    public UsuarioDTO getById(Integer id) {
        return usuarioRepository.findById(id).map((Usuario u) -> UsuarioDTO
                .builder()
                .nome(u.getNome())
                .email(u.getEmail())
                .nome(u.getNome())
                .senha(u.getSenha())
                .dataCriacao(u.getDataCriacao())
                .dataEdicao(u.getDataEdicao())
                .perfil(u.getPerfil())
                .build()).orElseThrow(() -> new RegraNegocioException(NÃO_ENCONTRADO));
    }

    @Override
    @Transactional
    public void put(TuristaDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.getUsuarioDTO().getId())
                .orElseThrow(() -> new RegraNegocioException(NÃO_ENCONTRADO));

        if(usuario.getTurista().getId() != dto.getId()) {
            throw new RegraNegocioException("Turista não pertence a esse usuário.");
        }

        usuario.setEmail(dto.getUsuarioDTO().getEmail());
        usuario.setNome(dto.getUsuarioDTO().getNome());
        usuario.setSenha(passwordEncoder.encode(dto.getUsuarioDTO().getSenha()));
        usuario.setDataEdicao(DataUtils.getDataAtualComMascara());
        usuario.setPerfil(dto.getUsuarioDTO().getPerfil());

        usuarioRepository.save(usuario);

    }

    @Override
    @Transactional
    public void delete(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = null;
        usuario = usuarioRepository.findByEmailOrNome(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(NÃO_ENCONTRADO);
        }

        String[] roles = "Administrador".equalsIgnoreCase(usuario.getPerfil()) ? new String[] { "ADMIN", "USER" }
                : new String[] { "USER" };
        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }

    @Override
    public UserDetails auth(Usuario usuario) {
        UserDetails user = loadUserByUsername(usuario.getEmail());
        boolean senhaOK = passwordEncoder.matches(usuario.getSenha(), user.getPassword());
        if (senhaOK) {
            return user;
        }
        throw new RegraNegocioException("Senha inválida.");
    }

}
