package com.api.turistae.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.turistae.dtos.DadosReviewDTO;
import com.api.turistae.dtos.ReviewDTO;
import com.api.turistae.dtos.TurismoDTO;
import com.api.turistae.dtos.TuristaDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Review;
import com.api.turistae.models.Turismo;
import com.api.turistae.models.Turista;
import com.api.turistae.repositorys.ReviewRepository;
import com.api.turistae.repositorys.TurismoRepository;
import com.api.turistae.repositorys.TuristaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

        /**
         * Mude a mensagem de turista não encontrado para review
         */
        private static final String TURISTA_NAO_ENCONTRADO = "Turista não encontrado.";

        /**
         * Mude a mensagem de turismo não encontrado para review
         */
        private static final String TURISMO_NAO_ENCONTRADO = "Turismo não encontrado.";

        /**
         * Mude a mensagem de não encontrado para review
         */
        private static final String NAO_ENCONTRADO = "Review não encontrado.";

        // Atributos
        private final ReviewRepository reviewRepository;
        private final TurismoRepository turismoRepository;
        private final TuristaRepository turistaRepository;

        // Métodos
        @Override
        @Transactional
        public Long post(ReviewDTO dto) {

                Turismo turis = turismoRepository
                                .findById(dto.getTurismoId())
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                Turista turista = turistaRepository
                                .findById(dto.getTuristaId())
                                .orElseThrow(() -> new RegraNegocioException(TURISTA_NAO_ENCONTRADO));

                Review review = new Review();

                review.setTexto(dto.getTexto());
                review.setNota(dto.getNota());
                review.setDataCriacao(dto.getDataCriacao());
                review.setDataEdicao(dto.getDataEdicao());
                review.setTurismo(turis);
                review.setTurista(turista);

                Review reviewGerado;

                reviewGerado = reviewRepository.save(review);

                return reviewGerado.getId();
        }

        @Override
        @Transactional
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
                                                .cadastroNacionalPessoasJuridicas(
                                                                r.getTurismo().getCadastroNacionalPessoasJuridicas())
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
                                                .turistaId(r.getTurismo().getTurista().getId())
                                                .build())

                                // Relacionamentos
                                .turista(TuristaDTO.builder()
                                                .id(r.getTurista().getId())
                                                .bairro(r.getTurista().getBairro())
                                                .cadastroPessoaFisica(r.getTurista().getCadastroPessoaFisica())
                                                .cidade(r.getTurista().getCidade())
                                                .dataCriacao(r.getTurista().getDataCriacao())
                                                .dataEdicao(r.getTurista().getDataEdicao())
                                                .dataNascimento(r.getTurista().getDataNascimento())
                                                .estado(r.getTurista().getEstado())
                                                .nome(r.getTurista().getNome())
                                                .numeroCasa(r.getTurista().getNumeroCasa())
                                                .profissao(r.getTurista().getProfissao())
                                                .registroGeral(r.getTurista().getRegistroGeral())
                                                .rua(r.getTurista().getRua())
                                                .telefone(r.getTurista().getTelefone())
                                                .build())
                                .build()).collect(Collectors.toList());

        }

        @Override
        @Transactional
        public List<DadosReviewDTO> getByTurismo(Long id) {

                Turismo turis = turismoRepository
                                .findById(id)
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                return reviewRepository.findAllByTurismo(turis).stream().map((Review r) -> DadosReviewDTO.builder()
                                .id(r.getId())
                                .dataCriacao(r.getDataCriacao())
                                .dataEdicao(r.getDataEdicao())
                                .nota(r.getNota())
                                .texto(r.getTexto())
                                .turismo(TurismoDTO.builder()
                                                .id(r.getTurismo().getId())
                                                .bairro(r.getTurismo().getBairro())
                                                .cadastroNacionalPessoasJuridicas(
                                                                r.getTurismo().getCadastroNacionalPessoasJuridicas())
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
                                                .turistaId(r.getTurismo().getTurista().getId())
                                                .build())

                                // Relacionamentos
                                .turista(TuristaDTO.builder()
                                                .id(r.getTurista().getId())
                                                .bairro(r.getTurista().getBairro())
                                                .cadastroPessoaFisica(r.getTurista().getCadastroPessoaFisica())
                                                .cidade(r.getTurista().getCidade())
                                                .dataCriacao(r.getTurista().getDataCriacao())
                                                .dataEdicao(r.getTurista().getDataEdicao())
                                                .dataNascimento(r.getTurista().getDataNascimento())
                                                .estado(r.getTurista().getEstado())
                                                .nome(r.getTurista().getNome())
                                                .numeroCasa(r.getTurista().getNumeroCasa())
                                                .profissao(r.getTurista().getProfissao())
                                                .registroGeral(r.getTurista().getRegistroGeral())
                                                .rua(r.getTurista().getRua())
                                                .telefone(r.getTurista().getTelefone())
                                                .build())
                                .build()).collect(Collectors.toList());

        }

        @Override
        @Transactional
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
                                                .cadastroNacionalPessoasJuridicas(
                                                                r.getTurismo().getCadastroNacionalPessoasJuridicas())
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
                                                .turistaId(r.getTurismo().getTurista().getId())
                                                .build())

                                // Relacionamentos
                                .turista(TuristaDTO.builder()
                                                .id(r.getTurista().getId())
                                                .bairro(r.getTurista().getBairro())
                                                .cadastroPessoaFisica(r.getTurista().getCadastroPessoaFisica())
                                                .cidade(r.getTurista().getCidade())
                                                .dataCriacao(r.getTurista().getDataCriacao())
                                                .dataEdicao(r.getTurista().getDataEdicao())
                                                .dataNascimento(r.getTurista().getDataNascimento())
                                                .estado(r.getTurista().getEstado())
                                                .nome(r.getTurista().getNome())
                                                .numeroCasa(r.getTurista().getNumeroCasa())
                                                .profissao(r.getTurista().getProfissao())
                                                .registroGeral(r.getTurista().getRegistroGeral())
                                                .rua(r.getTurista().getRua())
                                                .telefone(r.getTurista().getTelefone())
                                                .build())

                                .build()).orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));
        }

        @Override
        @Transactional
        public void put(Long id, ReviewDTO dto) {

                Turismo turi = turismoRepository.findById(dto.getTurismoId())
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                Turista turista = turistaRepository.findById(dto.getTuristaId())
                                .orElseThrow(() -> new RegraNegocioException(TURISTA_NAO_ENCONTRADO));

                Review review = reviewRepository.findById(id)
                                .orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));

                review.setDataCriacao(dto.getDataCriacao());
                review.setDataEdicao(dto.getDataEdicao());
                review.setNota(dto.getNota());
                review.setTexto(dto.getTexto());
                review.setTurismo(turi);
                review.setTurista(turista);

                reviewRepository.save(review);

        }

        @Override
        @Transactional
        public void delete(Long id) {
                reviewRepository.deleteById(id);
        }

}
