package com.api.turistae.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.turistae.dtos.CategoriaDTO;
import com.api.turistae.dtos.DadosTurismoDTO;
import com.api.turistae.dtos.TurismoDTO;
import com.api.turistae.dtos.ImagemDTO;
import com.api.turistae.dtos.CurtidaDTO;
import com.api.turistae.dtos.ReviewDTO;
import com.api.turistae.dtos.TuristaDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Categoria;
import com.api.turistae.models.Turismo;
import com.api.turistae.models.Imagem;
import com.api.turistae.models.Curtida;
import com.api.turistae.models.Review;
import com.api.turistae.models.Turista;
import com.api.turistae.repositorys.CategoriaRepository;
import com.api.turistae.repositorys.TurismoRepository;
import com.api.turistae.repositorys.TuristaRepository;
import com.api.turistae.utils.DataUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TurismoServiceImpl implements TurismoService {

        /**
         * Mude a mensagem de turista não encontrado para turismo
         */
        private static final String TURISTA_NAO_ENCONTRADO = "Turista não encontrado.";

        /**
         * Mude a mensagem de categoria não encontrado para turismo
         */
        private static final String CATEGORIA_NAO_ENCONTRADA = "Categoria não encontrada.";

        /**
         * Mude a mensagem de não encontrado para turismo
         */
        private static final String NAO_ENCONTRADO = "Turismo não encontrado.";

        // Atributos
        private final TurismoRepository turismoRepository;
        private final CategoriaRepository categoriaRepository;
        private final TuristaRepository turistaRepository;

        // Métodos
        @Override
        @Transactional
        public Long post(TurismoDTO dto) {

                Categoria categ = categoriaRepository
                                .findById(dto.getCategoriaId())
                                .orElseThrow(() -> new RegraNegocioException(CATEGORIA_NAO_ENCONTRADA));

                Turista turista = turistaRepository
                                .findById(dto.getTuristaId())
                                .orElseThrow(() -> new RegraNegocioException(TURISTA_NAO_ENCONTRADO));

                Turismo turismo = new Turismo();

                turismo.setBairro(dto.getBairro());
                turismo.setCadastroNacionalPessoasJuridicas(dto.getCadastroNacionalPessoasJuridicas());
                turismo.setCidade(dto.getCidade());
                turismo.setDataCriacao(DataUtils.getDataAtualComMascara());
                turismo.setDataEdicao(DataUtils.getDataAtualComMascara());
                turismo.setDescricao(dto.getDescricao());
                turismo.setEstado(dto.getEstado());
                turismo.setNome(dto.getNome());
                turismo.setNumeroLocal(dto.getNumeroLocal());
                turismo.setRua(dto.getRua());
                turismo.setTelefone(dto.getTelefone());
                turismo.setTurista(turista);
                turismo.setCategoria(categ);

                Turismo turismoGerado;

                turismoGerado = turismoRepository.save(turismo);

                return turismoGerado.getId();

        }

        @Override
        @Transactional
        public List<DadosTurismoDTO> getAll() {

                return turismoRepository.findAll().stream().map((Turismo t) -> DadosTurismoDTO.builder()
                                .id(t.getId())
                                .bairro(t.getBairro())
                                .cadastroNacionalPessoasJuridicas(t.getCadastroNacionalPessoasJuridicas())
                                .cidade(t.getBairro())
                                .dataCriacao(t.getDataCriacao())
                                .dataEdicao(t.getDataEdicao())
                                .descricao(t.getDescricao())
                                .estado(t.getEstado())
                                .nome(t.getNome())
                                .numeroLocal(t.getNumeroLocal())
                                .rua(t.getRua())
                                .telefone(t.getTelefone())
                                .categoria(CategoriaDTO.builder()
                                                .dataCriacao(t.getCategoria().getDataCriacao())
                                                .dataEdicao(t.getCategoria().getDataEdicao())
                                                .id(t.getCategoria().getId())
                                                .nome(t.getCategoria().getNome())
                                                .build())
                                .turista(TuristaDTO.builder()
                                                .id(t.getTurista().getId())
                                                .bairro(t.getTurista().getBairro())
                                                .cadastroPessoaFisica(t.getTurista().getCadastroPessoaFisica())
                                                .cidade(t.getTurista().getCidade())
                                                .dataCriacao(t.getTurista().getDataCriacao())
                                                .dataEdicao(t.getTurista().getDataEdicao())
                                                .dataNascimento(t.getTurista().getDataNascimento())
                                                .estado(t.getTurista().getEstado())
                                                .nome(t.getTurista().getNome())
                                                .numeroCasa(t.getTurista().getNumeroCasa())
                                                .profissao(t.getTurista().getProfissao())
                                                .registroGeral(t.getTurista().getRegistroGeral())
                                                .rua(t.getTurista().getRua())
                                                .telefone(t.getTurista().getTelefone())
                                                .build())

                                // Relacionamentos
                                .curtidas(t.getCurtidas().stream().map((Curtida c) -> CurtidaDTO.builder()
                                                .id(c.getId())
                                                .dataCriacao(c.getDataCriacao())
                                                .dataEdicao(c.getDataEdicao())
                                                .turismoId(c.getTurismo().getId())
                                                .turistaId(c.getTurista().getId())
                                                .build()).collect(Collectors.toList()))
                                .reviews(t.getReviews().stream().map((Review r) -> ReviewDTO.builder()
                                                .id(r.getId())
                                                .dataCriacao(r.getDataCriacao())
                                                .dataEdicao(r.getDataEdicao())
                                                .nota(r.getNota())
                                                .texto(r.getTexto())
                                                .turismoId(r.getTurismo().getId())
                                                .turistaId(r.getTurista().getId())
                                                .build()).collect(Collectors.toList()))
                                .imagens(t.getImagens().stream().map((Imagem i) -> ImagemDTO.builder()
                                                .id(i.getId())
                                                .dataCriacao(i.getDataCriacao())
                                                .dataEdicao(i.getDataEdicao())
                                                .turismoId(i.getTurismo().getId())
                                                .string64(i.getString64())
                                                .build()).collect(Collectors.toList()))

                                .build()).collect(Collectors.toList());
        }

        @Override
        @Transactional
        public List<DadosTurismoDTO> getAllOrderByCurtidas() {
                return turismoRepository.findAllByOrderByCurtidasDesc().stream().map((Turismo t) -> DadosTurismoDTO
                                .builder()
                                .id(t.getId())
                                .bairro(t.getBairro())
                                .cadastroNacionalPessoasJuridicas(t.getCadastroNacionalPessoasJuridicas())
                                .cidade(t.getBairro())
                                .dataCriacao(t.getDataCriacao())
                                .dataEdicao(t.getDataEdicao())
                                .descricao(t.getDescricao())
                                .estado(t.getEstado())
                                .nome(t.getNome())
                                .numeroLocal(t.getNumeroLocal())
                                .rua(t.getRua())
                                .telefone(t.getTelefone())
                                .categoria(CategoriaDTO.builder()
                                                .dataCriacao(t.getCategoria().getDataCriacao())
                                                .dataEdicao(t.getCategoria().getDataEdicao())
                                                .id(t.getCategoria().getId())
                                                .nome(t.getCategoria().getNome())
                                                .build())
                                .turista(TuristaDTO.builder()
                                                .id(t.getTurista().getId())
                                                .bairro(t.getTurista().getBairro())
                                                .cadastroPessoaFisica(t.getTurista().getCadastroPessoaFisica())
                                                .cidade(t.getTurista().getCidade())
                                                .dataCriacao(t.getTurista().getDataCriacao())
                                                .dataEdicao(t.getTurista().getDataEdicao())
                                                .dataNascimento(t.getTurista().getDataNascimento())
                                                .estado(t.getTurista().getEstado())
                                                .nome(t.getTurista().getNome())
                                                .numeroCasa(t.getTurista().getNumeroCasa())
                                                .profissao(t.getTurista().getProfissao())
                                                .registroGeral(t.getTurista().getRegistroGeral())
                                                .rua(t.getTurista().getRua())
                                                .telefone(t.getTurista().getTelefone())
                                                .build())

                                // Relacionamentos
                                .curtidas(t.getCurtidas().stream().map((Curtida c) -> CurtidaDTO.builder()
                                                .id(c.getId())
                                                .dataCriacao(c.getDataCriacao())
                                                .dataEdicao(c.getDataEdicao())
                                                .turismoId(c.getTurismo().getId())
                                                .turistaId(c.getTurista().getId())
                                                .build()).collect(Collectors.toList()))
                                .reviews(t.getReviews().stream().map((Review r) -> ReviewDTO.builder()
                                                .id(r.getId())
                                                .dataCriacao(r.getDataCriacao())
                                                .dataEdicao(r.getDataEdicao())
                                                .nota(r.getNota())
                                                .texto(r.getTexto())
                                                .turismoId(r.getTurismo().getId())
                                                .turistaId(r.getTurista().getId())
                                                .build()).collect(Collectors.toList()))
                                .imagens(t.getImagens().stream().map((Imagem i) -> ImagemDTO.builder()
                                                .id(i.getId())
                                                .dataCriacao(i.getDataCriacao())
                                                .dataEdicao(i.getDataEdicao())
                                                .turismoId(i.getTurismo().getId())
                                                .string64(i.getString64())
                                                .build()).collect(Collectors.toList()))

                                .build()).collect(Collectors.toList());
        }

        @Override
        @Transactional
        public List<DadosTurismoDTO> getAllByCategoria(Long id) {

                Categoria categ = categoriaRepository
                                .findById(id)
                                .orElseThrow(() -> new RegraNegocioException(CATEGORIA_NAO_ENCONTRADA));

                return turismoRepository.findAllByCategoria(categ).stream().map((Turismo t) -> DadosTurismoDTO
                                .builder()
                                .id(t.getId())
                                .bairro(t.getBairro())
                                .cadastroNacionalPessoasJuridicas(t.getCadastroNacionalPessoasJuridicas())
                                .cidade(t.getBairro())
                                .dataCriacao(t.getDataCriacao())
                                .dataEdicao(t.getDataEdicao())
                                .descricao(t.getDescricao())
                                .estado(t.getEstado())
                                .nome(t.getNome())
                                .numeroLocal(t.getNumeroLocal())
                                .rua(t.getRua())
                                .telefone(t.getTelefone())
                                .categoria(CategoriaDTO.builder()
                                                .dataCriacao(t.getCategoria().getDataCriacao())
                                                .dataEdicao(t.getCategoria().getDataEdicao())
                                                .id(t.getCategoria().getId())
                                                .nome(t.getCategoria().getNome())
                                                .build())
                                .turista(TuristaDTO.builder()
                                                .id(t.getTurista().getId())
                                                .bairro(t.getTurista().getBairro())
                                                .cadastroPessoaFisica(t.getTurista().getCadastroPessoaFisica())
                                                .cidade(t.getTurista().getCidade())
                                                .dataCriacao(t.getTurista().getDataCriacao())
                                                .dataEdicao(t.getTurista().getDataEdicao())
                                                .dataNascimento(t.getTurista().getDataNascimento())
                                                .estado(t.getTurista().getEstado())
                                                .nome(t.getTurista().getNome())
                                                .numeroCasa(t.getTurista().getNumeroCasa())
                                                .profissao(t.getTurista().getProfissao())
                                                .registroGeral(t.getTurista().getRegistroGeral())
                                                .rua(t.getTurista().getRua())
                                                .telefone(t.getTurista().getTelefone())
                                                .build())

                                // Relacionamentos
                                .curtidas(t.getCurtidas().stream().map((Curtida c) -> CurtidaDTO.builder()
                                                .id(c.getId())
                                                .dataCriacao(c.getDataCriacao())
                                                .dataEdicao(c.getDataEdicao())
                                                .turismoId(c.getTurismo().getId())
                                                .turistaId(c.getTurista().getId())
                                                .build()).collect(Collectors.toList()))
                                .reviews(t.getReviews().stream().map((Review r) -> ReviewDTO.builder()
                                                .id(r.getId())
                                                .dataCriacao(r.getDataCriacao())
                                                .dataEdicao(r.getDataEdicao())
                                                .nota(r.getNota())
                                                .texto(r.getTexto())
                                                .turismoId(r.getTurismo().getId())
                                                .turistaId(r.getTurista().getId())
                                                .build()).collect(Collectors.toList()))
                                .imagens(t.getImagens().stream().map((Imagem i) -> ImagemDTO.builder()
                                                .id(i.getId())
                                                .dataCriacao(i.getDataCriacao())
                                                .dataEdicao(i.getDataEdicao())
                                                .turismoId(i.getTurismo().getId())
                                                .string64(i.getString64())
                                                .build()).collect(Collectors.toList()))

                                .build()).collect(Collectors.toList());
        }

        @Override
        @Transactional
        public List<DadosTurismoDTO> getAllOrderByNota() {
                return turismoRepository.findAllOrderByMediaNotaDesc().stream().map((Turismo t) -> DadosTurismoDTO
                                .builder()
                                .id(t.getId())
                                .bairro(t.getBairro())
                                .cadastroNacionalPessoasJuridicas(t.getCadastroNacionalPessoasJuridicas())
                                .cidade(t.getBairro())
                                .dataCriacao(t.getDataCriacao())
                                .dataEdicao(t.getDataEdicao())
                                .descricao(t.getDescricao())
                                .estado(t.getEstado())
                                .nome(t.getNome())
                                .numeroLocal(t.getNumeroLocal())
                                .rua(t.getRua())
                                .telefone(t.getTelefone())
                                .categoria(CategoriaDTO.builder()
                                                .dataCriacao(t.getCategoria().getDataCriacao())
                                                .dataEdicao(t.getCategoria().getDataEdicao())
                                                .id(t.getCategoria().getId())
                                                .nome(t.getCategoria().getNome())
                                                .build())
                                .turista(TuristaDTO.builder()
                                                .id(t.getTurista().getId())
                                                .bairro(t.getTurista().getBairro())
                                                .cadastroPessoaFisica(t.getTurista().getCadastroPessoaFisica())
                                                .cidade(t.getTurista().getCidade())
                                                .dataCriacao(t.getTurista().getDataCriacao())
                                                .dataEdicao(t.getTurista().getDataEdicao())
                                                .dataNascimento(t.getTurista().getDataNascimento())
                                                .estado(t.getTurista().getEstado())
                                                .nome(t.getTurista().getNome())
                                                .numeroCasa(t.getTurista().getNumeroCasa())
                                                .profissao(t.getTurista().getProfissao())
                                                .registroGeral(t.getTurista().getRegistroGeral())
                                                .rua(t.getTurista().getRua())
                                                .telefone(t.getTurista().getTelefone())
                                                .build())

                                // Relacionamentos
                                .curtidas(t.getCurtidas().stream().map((Curtida c) -> CurtidaDTO.builder()
                                                .id(c.getId())
                                                .dataCriacao(c.getDataCriacao())
                                                .dataEdicao(c.getDataEdicao())
                                                .turismoId(c.getTurismo().getId())
                                                .turistaId(c.getTurista().getId())
                                                .build()).collect(Collectors.toList()))
                                .reviews(t.getReviews().stream().map((Review r) -> ReviewDTO.builder()
                                                .id(r.getId())
                                                .dataCriacao(r.getDataCriacao())
                                                .dataEdicao(r.getDataEdicao())
                                                .nota(r.getNota())
                                                .texto(r.getTexto())
                                                .turismoId(r.getTurismo().getId())
                                                .turistaId(r.getTurista().getId())
                                                .build()).collect(Collectors.toList()))
                                .imagens(t.getImagens().stream().map((Imagem i) -> ImagemDTO.builder()
                                                .id(i.getId())
                                                .dataCriacao(i.getDataCriacao())
                                                .dataEdicao(i.getDataEdicao())
                                                .turismoId(i.getTurismo().getId())
                                                .string64(i.getString64())
                                                .build()).collect(Collectors.toList()))

                                .build()).collect(Collectors.toList());
        }

        @Override
        @Transactional
        public DadosTurismoDTO getById(Long id) {

                return turismoRepository.findById(id).map((Turismo t) -> DadosTurismoDTO.builder()
                                .id(t.getId())
                                .cadastroNacionalPessoasJuridicas(t.getCadastroNacionalPessoasJuridicas())
                                .cidade(t.getBairro())
                                .dataCriacao(t.getDataCriacao())
                                .dataEdicao(t.getDataEdicao())
                                .descricao(t.getDescricao())
                                .estado(t.getEstado())
                                .nome(t.getNome())
                                .numeroLocal(t.getNumeroLocal())
                                .rua(t.getRua())
                                .telefone(t.getTelefone())
                                .categoria(CategoriaDTO.builder()
                                                .dataCriacao(t.getCategoria().getDataCriacao())
                                                .dataEdicao(t.getCategoria().getDataEdicao())
                                                .id(t.getCategoria().getId())
                                                .nome(t.getCategoria().getNome())
                                                .build())
                                .turista(TuristaDTO.builder()
                                                .id(t.getTurista().getId())
                                                .bairro(t.getTurista().getBairro())
                                                .cadastroPessoaFisica(t.getTurista().getCadastroPessoaFisica())
                                                .cidade(t.getTurista().getCidade())
                                                .dataCriacao(t.getTurista().getDataCriacao())
                                                .dataEdicao(t.getTurista().getDataEdicao())
                                                .dataNascimento(t.getTurista().getDataNascimento())
                                                .estado(t.getTurista().getEstado())
                                                .nome(t.getTurista().getNome())
                                                .numeroCasa(t.getTurista().getNumeroCasa())
                                                .profissao(t.getTurista().getProfissao())
                                                .registroGeral(t.getTurista().getRegistroGeral())
                                                .rua(t.getTurista().getRua())
                                                .telefone(t.getTurista().getTelefone())
                                                .build())
                                // Relacionamentos
                                .curtidas(t.getCurtidas().stream().map((Curtida c) -> CurtidaDTO.builder()
                                                .id(c.getId())
                                                .turismoId(c.getTurismo().getId())
                                                .turistaId(c.getTurista().getId())
                                                .build()).collect(Collectors.toList()))
                                .reviews(t.getReviews().stream().map((Review r) -> ReviewDTO.builder()
                                                .id(r.getId())
                                                .dataCriacao(r.getDataCriacao())
                                                .dataEdicao(r.getDataEdicao())
                                                .nota(r.getNota())
                                                .texto(r.getTexto())
                                                .turismoId(r.getTurismo().getId())
                                                .turistaId(r.getTurista().getId())
                                                .build()).collect(Collectors.toList()))
                                .imagens(t.getImagens().stream().map((Imagem i) -> ImagemDTO.builder()
                                                .id(i.getId())
                                                .dataCriacao(i.getDataCriacao())
                                                .dataEdicao(i.getDataEdicao())
                                                .turismoId(i.getTurismo().getId())
                                                .string64(i.getString64())
                                                .build()).collect(Collectors.toList()))

                                .build()).orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));
        }

        @Override
        @Transactional
        public void put(Long id, TurismoDTO dto) {

                Turismo turismo = turismoRepository.findById(id)
                                .orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));

                Categoria categ = categoriaRepository.findById(dto.getCategoriaId())
                                .orElseThrow(() -> new RegraNegocioException(CATEGORIA_NAO_ENCONTRADA));

                Turista turista = turistaRepository.findById(dto.getTuristaId())
                                .orElseThrow(() -> new RegraNegocioException(TURISTA_NAO_ENCONTRADO));

                turismo.setBairro(dto.getBairro());
                turismo.setCadastroNacionalPessoasJuridicas(dto.getCadastroNacionalPessoasJuridicas());
                turismo.setCidade(dto.getCidade());
                turismo.setDataCriacao(dto.getDataCriacao());
                turismo.setDataEdicao(DataUtils.getDataAtualComMascara());
                turismo.setDescricao(dto.getDescricao());
                turismo.setEstado(dto.getEstado());
                turismo.setNome(dto.getNome());
                turismo.setNumeroLocal(dto.getNumeroLocal());
                turismo.setRua(dto.getRua());
                turismo.setTelefone(dto.getTelefone());
                turismo.setTurista(turista);
                turismo.setCategoria(categ);

                turismoRepository.save(turismo);

        }

        @Override
        @Transactional
        public void delete(Long id) {
                turismoRepository.deleteById(id);
        }

        @Override
        public Double calcularMediaNotasPorId(Long turismoId) {

                Turismo turismo = turismoRepository.findById(turismoId)
                                .orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));

                List<Review> reviews = turismo.getReviews();

                if (reviews.isEmpty()) {
                        return 0.0;
                }

                double somaNotas = 0.0;

                for (Review review : reviews) {
                        somaNotas += review.getNota();
                }

                return somaNotas / reviews.size();
        }

}
