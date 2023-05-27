package com.api.turistae.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.turistae.dtos.CurtidaDTO;
import com.api.turistae.dtos.DadosCurtidaDTO;
import com.api.turistae.dtos.TurismoDTO;
import com.api.turistae.dtos.TuristaDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Curtida;
import com.api.turistae.models.Turismo;
import com.api.turistae.models.Turista;
import com.api.turistae.repositories.CurtidaRepository;
import com.api.turistae.repositories.TurismoRepository;
import com.api.turistae.repositories.TuristaRepository;
import com.api.turistae.utils.DataUtils;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurtidaServiceImpl implements CurtidaService {

        /**
         * Mude a mensagem de turista não encontrado para curtida
         */
        private static final String TURISTA_NAO_ENCONTRADO = "Turista não encontrado.";

        /**
         * Mude a mensagem de turismo não encontrado para curtida
         */
        private static final String TURISMO_NAO_ENCONTRADO = "Turismo não encontrado.";

        /**
         * Mude a mensagem de não encontrado para curtida
         */
        private static final String NAO_ENCONTRADO = "Curtida não encontrado.";

        // Atributos
        private final CurtidaRepository curtidaRepository;
        private final TurismoRepository turismoRepository;
        private final TuristaRepository turistaRepository;

        // Métodos
        @Override
        @Transactional
        public Long post(CurtidaDTO dto) {

                Turismo turis = turismoRepository
                                .findById(dto.getTurismoId())
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                Turista turista = turistaRepository
                                .findById(dto.getTuristaId())
                                .orElseThrow(() -> new RegraNegocioException(TURISTA_NAO_ENCONTRADO));

                Curtida curtida = new Curtida();

                curtida.setDataCriacao(DataUtils.getDataAtualComMascara());
                curtida.setDataEdicao(DataUtils.getDataAtualComMascara());
                curtida.setTurismo(turis);
                curtida.setTurista(turista);

                Curtida curtidaGerada = curtidaRepository.save(curtida);

                return curtidaGerada.getId();
        }

        @Override
        @Transactional
        public List<DadosCurtidaDTO> getAll() {

                return curtidaRepository.findAll().stream().map((Curtida c) -> DadosCurtidaDTO.builder()
                                .id(c.getId())
                                .dataCriacao(c.getDataCriacao())
                                .dataEdicao(c.getDataEdicao())
                                .turismo(TurismoDTO.builder()
                                                .id(c.getTurismo().getId())
                                                .bairro(c.getTurismo().getBairro())
                                                .cadastroNacionalPessoasJuridica(
                                                                c.getTurismo().getCadastroNacionalPessoasJuridica())
                                                .categoriaId(c.getTurismo().getCategoria().getId())
                                                .cidade(c.getTurismo().getCidade())
                                                .dataCriacao(c.getTurismo().getDataCriacao())
                                                .dataEdicao(c.getTurismo().getDataEdicao())
                                                .descricao(c.getTurismo().getDescricao())
                                                .estado(c.getTurismo().getEstado())
                                                .nome(c.getTurismo().getNome())
                                                .numeroLocal(c.getTurismo().getNumeroLocal())
                                                .rua(c.getTurismo().getRua())
                                                .telefone(c.getTurismo().getTelefone())
                                                .turistaId(c.getTurismo().getTurista().getId())
                                                .build())

                                // Relacionamentos
                                .turista(TuristaDTO.builder()
                                                .id(c.getTurista().getId())
                                                .bairro(c.getTurista().getBairro())
                                                .cadastroPessoaFisica(c.getTurista().getCadastroPessoaFisica())
                                                .cidade(c.getTurista().getCidade())
                                                .dataCriacao(c.getTurista().getDataCriacao())
                                                .dataEdicao(c.getTurista().getDataEdicao())
                                                .dataNascimento(c.getTurista().getDataNascimento())
                                                .estado(c.getTurista().getEstado())
                                                .nome(c.getTurista().getNome())
                                                .numeroCasa(c.getTurista().getNumeroCasa())
                                                .profissao(c.getTurista().getProfissao())
                                                .registroGeral(c.getTurista().getRegistroGeral())
                                                .rua(c.getTurista().getRua())
                                                .telefone(c.getTurista().getTelefone())
                                                .build())
                                .build()).collect(Collectors.toList());

        }

        @Override
        @Transactional
        public List<DadosCurtidaDTO> getByTurismo(Long id) {

                Turismo turis = turismoRepository
                                .findById(id)
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                return curtidaRepository.findAllByTurismo(turis).stream().map((Curtida c) -> DadosCurtidaDTO.builder()
                                .id(c.getId())
                                .dataCriacao(c.getDataCriacao())
                                .dataEdicao(c.getDataEdicao())
                                .turismo(TurismoDTO.builder()
                                                .id(c.getTurismo().getId())
                                                .bairro(c.getTurismo().getBairro())
                                                .cadastroNacionalPessoasJuridica(
                                                                c.getTurismo().getCadastroNacionalPessoasJuridica())
                                                .categoriaId(c.getTurismo().getCategoria().getId())
                                                .cidade(c.getTurismo().getCidade())
                                                .dataCriacao(c.getTurismo().getDataCriacao())
                                                .dataEdicao(c.getTurismo().getDataEdicao())
                                                .descricao(c.getTurismo().getDescricao())
                                                .estado(c.getTurismo().getEstado())
                                                .nome(c.getTurismo().getNome())
                                                .numeroLocal(c.getTurismo().getNumeroLocal())
                                                .rua(c.getTurismo().getRua())
                                                .telefone(c.getTurismo().getTelefone())
                                                .turistaId(c.getTurismo().getTurista().getId())
                                                .build())

                                // Relacionamentos
                                .turista(TuristaDTO.builder()
                                                .id(c.getTurista().getId())
                                                .bairro(c.getTurista().getBairro())
                                                .cadastroPessoaFisica(c.getTurista().getCadastroPessoaFisica())
                                                .cidade(c.getTurista().getCidade())
                                                .dataCriacao(c.getTurista().getDataCriacao())
                                                .dataEdicao(c.getTurista().getDataEdicao())
                                                .dataNascimento(c.getTurista().getDataNascimento())
                                                .estado(c.getTurista().getEstado())
                                                .nome(c.getTurista().getNome())
                                                .numeroCasa(c.getTurista().getNumeroCasa())
                                                .profissao(c.getTurista().getProfissao())
                                                .registroGeral(c.getTurista().getRegistroGeral())
                                                .rua(c.getTurista().getRua())
                                                .telefone(c.getTurista().getTelefone())
                                                .build())
                                .build()).collect(Collectors.toList());

        }

        @Override
        @Transactional
        public DadosCurtidaDTO getById(Long id) {

                return curtidaRepository.findById(id).map((Curtida c) -> DadosCurtidaDTO.builder()
                                .id(c.getId())
                                .dataCriacao(c.getDataCriacao())
                                .dataEdicao(c.getDataEdicao())
                                .turismo(TurismoDTO.builder()
                                                .id(c.getTurismo().getId())
                                                .bairro(c.getTurismo().getBairro())
                                                .cadastroNacionalPessoasJuridica(
                                                                c.getTurismo().getCadastroNacionalPessoasJuridica())
                                                .categoriaId(c.getTurismo().getCategoria().getId())
                                                .cidade(c.getTurismo().getCidade())
                                                .dataCriacao(c.getTurismo().getDataCriacao())
                                                .dataEdicao(c.getTurismo().getDataEdicao())
                                                .descricao(c.getTurismo().getDescricao())
                                                .estado(c.getTurismo().getEstado())
                                                .nome(c.getTurismo().getNome())
                                                .numeroLocal(c.getTurismo().getNumeroLocal())
                                                .rua(c.getTurismo().getRua())
                                                .telefone(c.getTurismo().getTelefone())
                                                .turistaId(c.getTurismo().getTurista().getId())
                                                .build())

                                // Relacionamentos
                                .turista(TuristaDTO.builder()
                                                .id(c.getTurista().getId())
                                                .bairro(c.getTurista().getBairro())
                                                .cadastroPessoaFisica(c.getTurista().getCadastroPessoaFisica())
                                                .cidade(c.getTurista().getCidade())
                                                .dataCriacao(c.getTurista().getDataCriacao())
                                                .dataEdicao(c.getTurista().getDataEdicao())
                                                .dataNascimento(c.getTurista().getDataNascimento())
                                                .estado(c.getTurista().getEstado())
                                                .nome(c.getTurista().getNome())
                                                .numeroCasa(c.getTurista().getNumeroCasa())
                                                .profissao(c.getTurista().getProfissao())
                                                .registroGeral(c.getTurista().getRegistroGeral())
                                                .rua(c.getTurista().getRua())
                                                .telefone(c.getTurista().getTelefone())
                                                .build())
                                .build()).orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));
        }

        @Override
        @Transactional
        public DadosCurtidaDTO getCurtidaByTurismoAndTurista(Long turistaId, Long turismoId) {

                Turismo turis = turismoRepository
                                .findById(turismoId)
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                Turista turista = turistaRepository
                                .findById(turistaId)
                                .orElseThrow(() -> new RegraNegocioException(TURISTA_NAO_ENCONTRADO));

                return curtidaRepository.findByTuristaAndTurismo(turista, turis).map((Curtida c) -> DadosCurtidaDTO.builder()
                                .id(c.getId())
                                .dataCriacao(c.getDataCriacao())
                                .dataEdicao(c.getDataEdicao())
                                .turismo(TurismoDTO.builder()
                                                .id(c.getTurismo().getId())
                                                .bairro(c.getTurismo().getBairro())
                                                .cadastroNacionalPessoasJuridica(
                                                                c.getTurismo().getCadastroNacionalPessoasJuridica())
                                                .categoriaId(c.getTurismo().getCategoria().getId())
                                                .cidade(c.getTurismo().getCidade())
                                                .dataCriacao(c.getTurismo().getDataCriacao())
                                                .dataEdicao(c.getTurismo().getDataEdicao())
                                                .descricao(c.getTurismo().getDescricao())
                                                .estado(c.getTurismo().getEstado())
                                                .nome(c.getTurismo().getNome())
                                                .numeroLocal(c.getTurismo().getNumeroLocal())
                                                .rua(c.getTurismo().getRua())
                                                .telefone(c.getTurismo().getTelefone())
                                                .turistaId(c.getTurismo().getTurista().getId())
                                                .build())

                                // Relacionamentos
                                .turista(TuristaDTO.builder()
                                                .id(c.getTurista().getId())
                                                .bairro(c.getTurista().getBairro())
                                                .cadastroPessoaFisica(c.getTurista().getCadastroPessoaFisica())
                                                .cidade(c.getTurista().getCidade())
                                                .dataCriacao(c.getTurista().getDataCriacao())
                                                .dataEdicao(c.getTurista().getDataEdicao())
                                                .dataNascimento(c.getTurista().getDataNascimento())
                                                .estado(c.getTurista().getEstado())
                                                .nome(c.getTurista().getNome())
                                                .numeroCasa(c.getTurista().getNumeroCasa())
                                                .profissao(c.getTurista().getProfissao())
                                                .registroGeral(c.getTurista().getRegistroGeral())
                                                .rua(c.getTurista().getRua())
                                                .telefone(c.getTurista().getTelefone())
                                                .build())
                                .build()).orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));

        }

        @Override
        @Transactional
        public void put(Long id, CurtidaDTO dto) {

                Turismo turi = turismoRepository.findById(dto.getTurismoId())
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                Turista turista = turistaRepository.findById(dto.getTuristaId())
                                .orElseThrow(() -> new RegraNegocioException(TURISTA_NAO_ENCONTRADO));

                Curtida curtida = curtidaRepository.findById(id)
                                .orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));

                curtida.setDataCriacao(dto.getDataCriacao());
                curtida.setDataEdicao(DataUtils.getDataAtualComMascara());
                curtida.setId(dto.getId());
                curtida.setTurismo(turi);
                curtida.setTurista(turista);

                curtidaRepository.save(curtida);

        }

        @Override
        @Transactional
        public void delete(Long id) {
                curtidaRepository.deleteById(id);
        }

}
