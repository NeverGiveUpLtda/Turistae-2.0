package com.api.turistae.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.turistae.dtos.CurtidaDTO;
import com.api.turistae.dtos.DadosUsuarioDTO;
import com.api.turistae.dtos.ReviewDTO;
import com.api.turistae.dtos.TurismoDTO;
import com.api.turistae.dtos.UsuarioDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Curtida;
import com.api.turistae.models.Review;
import com.api.turistae.models.Usuario;
import com.api.turistae.models.Turismo;
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

        usuarioGerado = usuarioRepository.save(usuario);

        return usuarioGerado.getId();

    }

    @Override
    @Transactional
    public List<DadosUsuarioDTO> getAll() {

        return usuarioRepository.findAll()
                .stream()
                .map((Usuario u) -> DadosUsuarioDTO.builder()
                        .id(u.getId())
                        .bairro(u.getBairro())
                        .cadastroPessoaFisica(u.getCadastroPessoaFisica())
                        .cidade(u.getCidade())
                        .dataCriacao(u.getDataCriacao())
                        .dataEdicao(u.getDataEdicao())
                        .dataNascimento(u.getDataNascimento())
                        .email(u.getEmail())
                        .estado(u.getEstado())
                        .bairro(u.getBairro())
                        .nome(u.getNome())
                        .nomeUsuario(u.getNomeUsuario())
                        .numeroCasa(u.getNumeroCasa())
                        .profissao(u.getProfissao())
                        .registroGeral(u.getRegistroGeral())
                        .rua(u.getRua())
                        .senha(u.getSenha())
                        .telefone(u.getTelefone())
                        .dataCriacao(u.getDataCriacao())
                        .dataEdicao(u.getDataEdicao())

                        // Relacionamentos
                        .curtidas(u.getCurtidas().stream().map((Curtida c) -> CurtidaDTO.builder()
                                .id(c.getId())
                                .turismoId(c.getTurismo().getId())
                                .usuarioId(c.getUsuario().getId())
                                .dataCriacao(c.getDataCriacao())
                                .dataEdicao(c.getDataEdicao())
                                .build()).collect(Collectors.toList()))
                        .reviews(u.getReviews().stream().map((Review r) -> ReviewDTO.builder()
                                .id(r.getId())
                                .dataCriacao(r.getDataCriacao())
                                .dataEdicao(r.getDataEdicao())
                                .nota(r.getNota())
                                .texto(r.getTexto())
                                .turismoId(r.getTurismo().getId())
                                .usuarioId(r.getUsuario().getId())
                                .build()).collect(Collectors.toList()))
                        .turismos(u.getTurismos().stream().map((Turismo t) -> TurismoDTO.builder()
                                .id(t.getId())
                                .cadastroNacionalPessoasJuridicas(t.getCadastroNacionalPessoasJuridicas())
                                .categoriaId(t.getCategoria().getId())
                                .cidade(t.getCidade())
                                .dataCriacao(t.getDataCriacao())
                                .dataEdicao(t.getDataEdicao())
                                .descricao(t.getDescricao())
                                .estado(t.getEstado())
                                .nome(t.getNome())
                                .numeroLocal(t.getNumeroLocal())
                                .rua(t.getRua())
                                .telefone(t.getTelefone())
                                .usuarioId(t.getUsuario().getId())
                                .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public DadosUsuarioDTO getById(Long id) {
        return usuarioRepository.findById(id).map((Usuario u) -> DadosUsuarioDTO.builder()
                .id(u.getId())
                .bairro(u.getBairro())
                .cadastroPessoaFisica(u.getCadastroPessoaFisica())
                .cidade(u.getCidade())
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
                .rua(u.getRua())
                .senha(u.getSenha())
                .telefone(u.getTelefone())
                .dataCriacao(u.getDataCriacao())
                .dataEdicao(u.getDataEdicao())

                // Relacionamentos
                .curtidas(u.getCurtidas().stream().map((Curtida c) -> CurtidaDTO.builder()
                        .id(c.getId())
                        .dataCriacao(c.getDataCriacao())
                        .dataEdicao(c.getDataEdicao())
                        .turismoId(c.getTurismo().getId())
                        .usuarioId(c.getUsuario().getId())
                        .build()).collect(Collectors.toList()))
                .reviews(u.getReviews().stream().map((Review r) -> ReviewDTO.builder()
                        .id(r.getId())
                        .dataCriacao(r.getDataCriacao())
                        .dataEdicao(r.getDataEdicao())
                        .nota(r.getNota())
                        .texto(r.getTexto())
                        .turismoId(r.getTurismo().getId())
                        .usuarioId(r.getUsuario().getId())
                        .build()).collect(Collectors.toList()))
                .turismos(u.getTurismos().stream().map((Turismo t) -> TurismoDTO.builder()
                        .id(t.getId())
                        .cadastroNacionalPessoasJuridicas(t.getCadastroNacionalPessoasJuridicas())
                        .categoriaId(t.getCategoria().getId())
                        .cidade(t.getCidade())
                        .dataCriacao(t.getDataCriacao())
                        .dataEdicao(t.getDataEdicao())
                        .descricao(t.getDescricao())
                        .estado(t.getEstado())
                        .nome(t.getNome())
                        .numeroLocal(t.getNumeroLocal())
                        .rua(t.getRua())
                        .telefone(t.getTelefone())
                        .usuarioId(t.getUsuario().getId())
                        .build()).collect(Collectors.toList()))
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

        usuarioRepository.save(usuario);

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
                .rua(u.getRua())
                .senha(u.getSenha())
                .telefone(u.getTelefone())
                .dataCriacao(u.getDataCriacao())
                .dataEdicao(u.getDataEdicao())
                .build()).orElseThrow(() -> new RegraNegocioException("Usuário ou senha inválidos."));
    }

}
