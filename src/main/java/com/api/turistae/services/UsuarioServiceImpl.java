package com.api.turistae.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.api.turistae.dtos.DadosUsuarioDTO;
import com.api.turistae.dtos.UsuarioDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Usuario;
import com.api.turistae.repositorys.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    // Atributos
    private final UsuarioRepository usuarioRepository;

    // Métodos
    @Override
    @Transactional
    public Long post(UsuarioDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setSenha(dto.getSenha());
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setNumeroCasa(dto.getNumeroCasa());
        usuario.setRua(dto.getRua());
        usuario.setBairro(dto.getBairro());
        usuario.setCidade(dto.getCidade());
        usuario.setEstado(dto.getEstado());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setProfissao(dto.getProfissao());
        usuario.setCadastroPessoaFisica(dto.getCadastroPessoaFisica());
        usuario.setRegistroGeral(dto.getRegistroGeral());
        usuario.setDataCriacao(dto.getDataCriacao());
        usuario.setDataEdicao(dto.getDataEdicao());
        Usuario usuarioGerado;

        try {
            usuarioGerado = usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException("Erro: " + e.getMessage());
        }

        return usuarioGerado.getId();

    }

    @Override
    @Transactional
    public List<DadosUsuarioDTO> getAll() {

        return usuarioRepository.findAll()
                .stream()
                .map((Usuario u) -> DadosUsuarioDTO.builder()
                        .id(u.getId())
                        .cadastroPessoaFisica(u.getCadastroPessoaFisica())
                        .cidade(u.getCidade())
                        .curtidas(u.getCurtidas())
                        .dataCriacao(u.getDataCriacao())
                        .dataEdicao(u.getDataEdicao())
                        .dataNascimento(u.getDataNascimento())
                        .email(u.getEmail())
                        .estado(u.getEstado())
                        .nome(u.getNome())
                        .nomeUsuario(u.getNomeUsuario())
                        .numeroCasa(u.getNumeroCasa())
                        .profissao(u.getProfissao())
                        .registroGeral(u.getRegistroGeral())
                        .reviews(u.getReviews())
                        .rua(u.getRua())
                        .senha(u.getSenha())
                        .telefone(u.getTelefone())
                        .turismos(u.getTurismos())
                        .vouchers(u.getVouchers())
                        .dataCriacao(u.getDataCriacao())
                        .dataEdicao(u.getDataEdicao())
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public DadosUsuarioDTO getById(Long id) {
        return usuarioRepository.findById(id).map((Usuario u) -> DadosUsuarioDTO.builder()
                .id(u.getId())
                .cadastroPessoaFisica(u.getCadastroPessoaFisica())
                .cidade(u.getCidade())
                .curtidas(u.getCurtidas())
                .dataCriacao(u.getDataCriacao())
                .dataEdicao(u.getDataEdicao())
                .dataNascimento(u.getDataNascimento())
                .email(u.getEmail())
                .estado(u.getEstado())
                .nome(u.getNome())
                .nomeUsuario(u.getNomeUsuario())
                .numeroCasa(u.getNumeroCasa())
                .profissao(u.getProfissao())
                .registroGeral(u.getRegistroGeral())
                .reviews(u.getReviews())
                .rua(u.getRua())
                .senha(u.getSenha())
                .telefone(u.getTelefone())
                .turismos(u.getTurismos())
                .vouchers(u.getVouchers())
                .dataCriacao(u.getDataCriacao())
                .dataEdicao(u.getDataEdicao())
                .build()).orElseThrow(() -> new RegraNegocioException("Usuário não encontrado."));
    }

    @Override
    @Transactional
    public void put(Long id, UsuarioDTO dto) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado."));

        usuario.setNomeUsuario(dto.getNomeUsuario());
        usuario.setSenha(dto.getSenha());
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setNumeroCasa(dto.getNumeroCasa());
        usuario.setRua(dto.getRua());
        usuario.setBairro(dto.getBairro());
        usuario.setCidade(dto.getCidade());
        usuario.setEstado(dto.getEstado());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setProfissao(dto.getProfissao());
        usuario.setCadastroPessoaFisica(dto.getCadastroPessoaFisica());
        usuario.setRegistroGeral(dto.getRegistroGeral());
        usuario.setDataCriacao(dto.getDataCriacao());
        usuario.setDataEdicao(dto.getDataEdicao());

        try {
            usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException("Campo vazio no objeto: " + usuario.toString());
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public DadosUsuarioDTO login(String usuario, String senha) {
        return usuarioRepository.login(usuario, usuario, senha).map((Usuario u) -> DadosUsuarioDTO.builder()
                .id(u.getId())
                .cadastroPessoaFisica(u.getCadastroPessoaFisica())
                .cidade(u.getCidade())
                .curtidas(u.getCurtidas())
                .dataCriacao(u.getDataCriacao())
                .dataEdicao(u.getDataEdicao())
                .dataNascimento(u.getDataNascimento())
                .email(u.getEmail())
                .estado(u.getEstado())
                .nome(u.getNome())
                .nomeUsuario(u.getNomeUsuario())
                .numeroCasa(u.getNumeroCasa())
                .profissao(u.getProfissao())
                .registroGeral(u.getRegistroGeral())
                .reviews(u.getReviews())
                .rua(u.getRua())
                .senha(u.getSenha())
                .telefone(u.getTelefone())
                .turismos(u.getTurismos())
                .vouchers(u.getVouchers())
                .dataCriacao(u.getDataCriacao())
                .dataEdicao(u.getDataEdicao())
                .build()).orElseThrow(() -> new RegraNegocioException("Usuário ou senha inválidos."));
    }

}
