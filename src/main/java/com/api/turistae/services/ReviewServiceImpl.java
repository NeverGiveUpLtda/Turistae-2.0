package com.api.turistae.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.turistae.dtos.DadosReviewDTO;
import com.api.turistae.dtos.ReviewDTO;
import com.api.turistae.dtos.TurismoDTO;
import com.api.turistae.dtos.UsuarioDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Review;
import com.api.turistae.models.Turismo;
import com.api.turistae.models.Usuario;
import com.api.turistae.repositorys.ReviewRepository;
import com.api.turistae.repositorys.TurismoRepository;
import com.api.turistae.repositorys.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    // Atributos
    private final ReviewRepository reviewRepository;
    private final TurismoRepository turismoRepository;
    private final UsuarioRepository usuarioRepository;

    // Métodos
    @Override
    @Transactional
    public Long post(ReviewDTO dto) {

        Turismo turis = turismoRepository
                .findById(dto.getTurismoId())
                .orElseThrow(() -> new RegraNegocioException("Turismo não encontrado."));

        Usuario usu = usuarioRepository
                .findById(dto.getUsuarioId())
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado."));

        Review review = new Review();

        review.setTexto(dto.getTexto());
        review.setNota(dto.getNota());
        review.setDataCriacao(dto.getDataCriacao());
        review.setDataEdicao(dto.getDataEdicao());
        review.setTurismo(turis);
        review.setUsuario(usu);

        Review reviewGerado;

        reviewGerado = reviewRepository.save(review);

        return reviewGerado.getId();
    }

    @Override
    public List<DadosReviewDTO> getAll() {

        return reviewRepository.findAll().stream().map((Review r) -> DadosReviewDTO.builder()
                .id(r.getId())
                .dataCriacao(r.getDataCriacao())
                .dataEdicao(r.getDataEdicao())
                .nota(r.getNota())
                .texto(r.getTexto())
                .turismo(TurismoDTO.builder()
                        .id(r.getTurismo().getId())
                        .bairro(r.getTurismo().getBairro())
                        .cadastroNacionalPessoasJuridicas(r.getTurismo().getCadastroNacionalPessoasJuridicas())
                        .categoriaId(r.getTurismo().getCategoria().getId())
                        .cidade(r.getTurismo().getCidade())
                        .dataCriacao(r.getTurismo().getDataCriacao())
                        .dataEdicao(r.getTurismo().getDataEdicao())
                        .descricao(r.getTurismo().getDescricao())
                        .estado(r.getTurismo().getEstado())
                        .nome(r.getTurismo().getNome())
                        .numeroLocal(r.getTurismo().getNumeroLocal())
                        .rua(r.getTurismo().getRua())
                        .telefone(r.getTurismo().getTelefone())
                        .usuarioId(r.getTurismo().getUsuario().getId())
                        .build())

                // Relacionamentos
                .usuario(UsuarioDTO.builder()
                        .id(r.getUsuario().getId())
                        .bairro(r.getUsuario().getBairro())
                        .cadastroPessoaFisica(r.getUsuario().getCadastroPessoaFisica())
                        .cidade(r.getUsuario().getCidade())
                        .dataCriacao(r.getUsuario().getDataCriacao())
                        .dataEdicao(r.getUsuario().getDataEdicao())
                        .dataNascimento(r.getUsuario().getDataNascimento())
                        .email(r.getUsuario().getEmail())
                        .estado(r.getUsuario().getEstado())
                        .nome(r.getUsuario().getNome())
                        .nomeUsuario(r.getUsuario().getNomeUsuario())
                        .numeroCasa(r.getUsuario().getNumeroCasa())
                        .profissao(r.getUsuario().getProfissao())
                        .registroGeral(r.getUsuario().getRegistroGeral())
                        .rua(r.getUsuario().getRua())
                        .senha(r.getUsuario().getSenha())
                        .telefone(r.getUsuario().getTelefone())
                        .build())
                .build()).collect(Collectors.toList());

    }

    @Override
    public DadosReviewDTO getById(Long id) {

        return reviewRepository.findById(id).map((Review r) -> DadosReviewDTO.builder()
                .id(r.getId())
                .dataCriacao(r.getDataCriacao())
                .dataEdicao(r.getDataEdicao())
                .nota(r.getNota())
                .texto(r.getTexto())
                .turismo(TurismoDTO.builder()
                        .id(r.getTurismo().getId())
                        .bairro(r.getTurismo().getBairro())
                        .cadastroNacionalPessoasJuridicas(r.getTurismo().getCadastroNacionalPessoasJuridicas())
                        .categoriaId(r.getTurismo().getCategoria().getId())
                        .cidade(r.getTurismo().getCidade())
                        .dataCriacao(r.getTurismo().getDataCriacao())
                        .dataEdicao(r.getTurismo().getDataEdicao())
                        .descricao(r.getTurismo().getDescricao())
                        .estado(r.getTurismo().getEstado())
                        .nome(r.getTurismo().getNome())
                        .numeroLocal(r.getTurismo().getNumeroLocal())
                        .rua(r.getTurismo().getRua())
                        .telefone(r.getTurismo().getTelefone())
                        .usuarioId(r.getTurismo().getUsuario().getId())
                        .build())

                // Relacionamentos
                .usuario(UsuarioDTO.builder()
                        .id(r.getUsuario().getId())
                        .bairro(r.getUsuario().getBairro())
                        .cadastroPessoaFisica(r.getUsuario().getCadastroPessoaFisica())
                        .cidade(r.getUsuario().getCidade())
                        .dataCriacao(r.getUsuario().getDataCriacao())
                        .dataEdicao(r.getUsuario().getDataEdicao())
                        .dataNascimento(r.getUsuario().getDataNascimento())
                        .email(r.getUsuario().getEmail())
                        .estado(r.getUsuario().getEstado())
                        .nome(r.getUsuario().getNome())
                        .nomeUsuario(r.getUsuario().getNomeUsuario())
                        .numeroCasa(r.getUsuario().getNumeroCasa())
                        .profissao(r.getUsuario().getProfissao())
                        .registroGeral(r.getUsuario().getRegistroGeral())
                        .rua(r.getUsuario().getRua())
                        .senha(r.getUsuario().getSenha())
                        .telefone(r.getUsuario().getTelefone())
                        .build())
                        
                .build()).orElseThrow(() -> new RegraNegocioException("Turismo não encontrado."));
    }

    @Override
    public void put(Long id, ReviewDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'put'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
