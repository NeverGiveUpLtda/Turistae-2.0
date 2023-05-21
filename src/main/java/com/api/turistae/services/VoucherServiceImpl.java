package com.api.turistae.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.turistae.dtos.DadosVoucherDTO;
import com.api.turistae.dtos.TurismoDTO;
import com.api.turistae.dtos.TuristaDTO;
import com.api.turistae.dtos.VoucherDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Turismo;
import com.api.turistae.models.Turista;
import com.api.turistae.models.Voucher;
import com.api.turistae.repositorys.TurismoRepository;
import com.api.turistae.repositorys.TuristaRepository;
import com.api.turistae.repositorys.VoucherRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {

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
        private static final String NAO_ENCONTRADO = "Voucher não encontrado.";

        // Atributos
        private final VoucherRepository voucherRepository;
        private final TurismoRepository turismoRepository;
        private final TuristaRepository turistaRepository;

        @Override
        @Transactional
        public Long post(VoucherDTO dto) {

                Turismo turi = turismoRepository.findById(dto.getTurismoId())
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                Voucher voucher = new Voucher();

                voucher.setCodigo(dto.getCodigo());
                voucher.setValor(dto.getValor());
                voucher.setDataCriacao(dto.getDataCriacao());
                voucher.setDataEdicao(dto.getDataEdicao());
                voucher.setTurismo(turi);

                Voucher voucherGerado;

                voucherGerado = voucherRepository.save(voucher);

                return voucherGerado.getId();

        }

        @Override
        @Transactional
        public List<DadosVoucherDTO> getAll() {

                return voucherRepository.findAll().stream().map((Voucher v) -> {

                        if (v.getTurista() != null)
                                return DadosVoucherDTO.builder()
                                                .id(v.getId())
                                                .codigo(v.getCodigo())
                                                .valor(v.getValor())
                                                .dataCriacao(v.getDataCriacao())
                                                .dataEdicao(v.getDataEdicao())
                                                .turismo(TurismoDTO.builder()
                                                                .bairro(v.getTurismo().getBairro())
                                                                .cadastroNacionalPessoasJuridicas(
                                                                                v.getTurismo().getCadastroNacionalPessoasJuridicas())
                                                                .categoriaId(v.getTurismo().getCategoria().getId())
                                                                .cidade(v.getTurismo().getCidade())
                                                                .dataCriacao(v.getTurismo().getDataCriacao())
                                                                .dataEdicao(v.getTurismo().getDataEdicao())
                                                                .descricao(v.getTurismo().getDescricao())
                                                                .estado(v.getTurismo().getEstado())
                                                                .id(v.getTurismo().getId())
                                                                .nome(v.getTurismo().getNome())
                                                                .numeroLocal(v.getTurismo().getNumeroLocal())
                                                                .rua(v.getTurismo().getRua())
                                                                .telefone(v.getTurismo().getTelefone())
                                                                .turistaId(v.getTurismo().getTurista().getId())
                                                                .build())
                                                .turista(TuristaDTO.builder()
                                                                .bairro(v.getTurista().getBairro())
                                                                .cadastroPessoaFisica(v.getTurista()
                                                                                .getCadastroPessoaFisica())
                                                                .cidade(v.getTurista().getCidade())
                                                                .dataCriacao(v.getTurista().getDataCriacao())
                                                                .dataEdicao(v.getTurista().getDataEdicao())
                                                                .dataNascimento(v.getTurista().getDataNascimento())
                                                                .estado(v.getTurista().getEstado())
                                                                .id(v.getTurista().getId())
                                                                .nome(v.getTurista().getNome())
                                                                .numeroCasa(v.getTurista().getNumeroCasa())
                                                                .profissao(v.getTurista().getProfissao())
                                                                .registroGeral(v.getTurista().getRegistroGeral())
                                                                .rua(v.getTurista().getRua())
                                                                .telefone(v.getTurista().getTelefone())
                                                                .build())
                                                .build();

                        return DadosVoucherDTO.builder()
                                        .id(v.getId())
                                        .codigo(v.getCodigo())
                                        .valor(v.getValor())
                                        .dataCriacao(v.getDataCriacao())
                                        .dataEdicao(v.getDataEdicao())
                                        .turismo(TurismoDTO.builder()
                                                        .bairro(v.getTurismo().getBairro())
                                                        .cadastroNacionalPessoasJuridicas(
                                                                        v.getTurismo().getCadastroNacionalPessoasJuridicas())
                                                        .categoriaId(v.getTurismo().getCategoria().getId())
                                                        .cidade(v.getTurismo().getCidade())
                                                        .dataCriacao(v.getTurismo().getDataCriacao())
                                                        .dataEdicao(v.getTurismo().getDataEdicao())
                                                        .descricao(v.getTurismo().getDescricao())
                                                        .estado(v.getTurismo().getEstado())
                                                        .id(v.getTurismo().getId())
                                                        .nome(v.getTurismo().getNome())
                                                        .numeroLocal(v.getTurismo().getNumeroLocal())
                                                        .rua(v.getTurismo().getRua())
                                                        .telefone(v.getTurismo().getTelefone())
                                                        .turistaId(v.getTurismo().getTurista().getId())
                                                        .build())
                                        .build();

                }).collect(Collectors.toList());
        }

        @Override
        @Transactional
        public List<DadosVoucherDTO> getVouchersPorTurismo(Long id) {

                Turismo turi = turismoRepository.findById(id)
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                return voucherRepository.findAllByTurismo(turi).stream().map((Voucher v) -> {

                        if (v.getTurista() != null)
                                return DadosVoucherDTO.builder()
                                                .id(v.getId())
                                                .codigo(v.getCodigo())
                                                .valor(v.getValor())
                                                .dataCriacao(v.getDataCriacao())
                                                .dataEdicao(v.getDataEdicao())
                                                .turismo(TurismoDTO.builder()
                                                                .bairro(v.getTurismo().getBairro())
                                                                .cadastroNacionalPessoasJuridicas(
                                                                                v.getTurismo().getCadastroNacionalPessoasJuridicas())
                                                                .categoriaId(v.getTurismo().getCategoria().getId())
                                                                .cidade(v.getTurismo().getCidade())
                                                                .dataCriacao(v.getTurismo().getDataCriacao())
                                                                .dataEdicao(v.getTurismo().getDataEdicao())
                                                                .descricao(v.getTurismo().getDescricao())
                                                                .estado(v.getTurismo().getEstado())
                                                                .id(v.getTurismo().getId())
                                                                .nome(v.getTurismo().getNome())
                                                                .numeroLocal(v.getTurismo().getNumeroLocal())
                                                                .rua(v.getTurismo().getRua())
                                                                .telefone(v.getTurismo().getTelefone())
                                                                .turistaId(v.getTurismo().getTurista().getId())
                                                                .build())
                                                .turista(TuristaDTO.builder()
                                                                .bairro(v.getTurista().getBairro())
                                                                .cadastroPessoaFisica(v.getTurista()
                                                                                .getCadastroPessoaFisica())
                                                                .cidade(v.getTurista().getCidade())
                                                                .dataCriacao(v.getTurista().getDataCriacao())
                                                                .dataEdicao(v.getTurista().getDataEdicao())
                                                                .dataNascimento(v.getTurista().getDataNascimento())
                                                                .estado(v.getTurista().getEstado())
                                                                .id(v.getTurista().getId())
                                                                .nome(v.getTurista().getNome())
                                                                .numeroCasa(v.getTurista().getNumeroCasa())
                                                                .profissao(v.getTurista().getProfissao())
                                                                .registroGeral(v.getTurista().getRegistroGeral())
                                                                .rua(v.getTurista().getRua())
                                                                .telefone(v.getTurista().getTelefone())
                                                                .build())
                                                .build();
                        return DadosVoucherDTO.builder()
                                        .id(v.getId())
                                        .codigo(v.getCodigo())
                                        .valor(v.getValor())
                                        .dataCriacao(v.getDataCriacao())
                                        .dataEdicao(v.getDataEdicao())
                                        .turismo(TurismoDTO.builder()
                                                        .bairro(v.getTurismo().getBairro())
                                                        .cadastroNacionalPessoasJuridicas(
                                                                        v.getTurismo().getCadastroNacionalPessoasJuridicas())
                                                        .categoriaId(v.getTurismo().getCategoria().getId())
                                                        .cidade(v.getTurismo().getCidade())
                                                        .dataCriacao(v.getTurismo().getDataCriacao())
                                                        .dataEdicao(v.getTurismo().getDataEdicao())
                                                        .descricao(v.getTurismo().getDescricao())
                                                        .estado(v.getTurismo().getEstado())
                                                        .id(v.getTurismo().getId())
                                                        .nome(v.getTurismo().getNome())
                                                        .numeroLocal(v.getTurismo().getNumeroLocal())
                                                        .rua(v.getTurismo().getRua())
                                                        .telefone(v.getTurismo().getTelefone())
                                                        .turistaId(v.getTurismo().getTurista().getId())
                                                        .build())
                                        .build();

                }).collect(Collectors.toList());
        }

        @Override
        @Transactional
        public List<DadosVoucherDTO> getVouchersSemTurista(Long turismoId) {

                Turismo turi = turismoRepository.findById(turismoId)
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                return voucherRepository.findAllByTurismoAndTuristaIsNull(turi).stream()
                                .map((Voucher v) -> DadosVoucherDTO.builder()
                                                .id(v.getId())
                                                .codigo(v.getCodigo())
                                                .valor(v.getValor())
                                                .dataCriacao(v.getDataCriacao())
                                                .dataEdicao(v.getDataEdicao())
                                                .turismo(TurismoDTO.builder()
                                                                .bairro(v.getTurismo().getBairro())
                                                                .cadastroNacionalPessoasJuridicas(
                                                                                v.getTurismo().getCadastroNacionalPessoasJuridicas())
                                                                .categoriaId(v.getTurismo().getCategoria().getId())
                                                                .cidade(v.getTurismo().getCidade())
                                                                .dataCriacao(v.getTurismo().getDataCriacao())
                                                                .dataEdicao(v.getTurismo().getDataEdicao())
                                                                .descricao(v.getTurismo().getDescricao())
                                                                .estado(v.getTurismo().getEstado())
                                                                .id(v.getTurismo().getId())
                                                                .nome(v.getTurismo().getNome())
                                                                .numeroLocal(v.getTurismo().getNumeroLocal())
                                                                .rua(v.getTurismo().getRua())
                                                                .telefone(v.getTurismo().getTelefone())
                                                                .turistaId(v.getTurismo().getTurista().getId())
                                                                .build())
                                                .build()

                                ).collect(Collectors.toList());
        }

        @Override
        @Transactional
        public List<DadosVoucherDTO> getVouchersComTurista(Long id) {

                Turismo turi = turismoRepository.findById(id)
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                return voucherRepository.findAllByTurismoAndTuristaIsNotNull(turi).stream().map((Voucher v) ->

                DadosVoucherDTO.builder()
                                .id(v.getId())
                                .codigo(v.getCodigo())
                                .valor(v.getValor())
                                .dataCriacao(v.getDataCriacao())
                                .dataEdicao(v.getDataEdicao())
                                .turismo(TurismoDTO.builder()
                                                .bairro(v.getTurismo().getBairro())
                                                .cadastroNacionalPessoasJuridicas(
                                                                v.getTurismo().getCadastroNacionalPessoasJuridicas())
                                                .categoriaId(v.getTurismo().getCategoria().getId())
                                                .cidade(v.getTurismo().getCidade())
                                                .dataCriacao(v.getTurismo().getDataCriacao())
                                                .dataEdicao(v.getTurismo().getDataEdicao())
                                                .descricao(v.getTurismo().getDescricao())
                                                .estado(v.getTurismo().getEstado())
                                                .id(v.getTurismo().getId())
                                                .nome(v.getTurismo().getNome())
                                                .numeroLocal(v.getTurismo().getNumeroLocal())
                                                .rua(v.getTurismo().getRua())
                                                .telefone(v.getTurismo().getTelefone())
                                                .turistaId(v.getTurismo().getTurista().getId())
                                                .build())
                                .turista(TuristaDTO.builder()
                                                .bairro(v.getTurista().getBairro())
                                                .cadastroPessoaFisica(v.getTurista()
                                                                .getCadastroPessoaFisica())
                                                .cidade(v.getTurista().getCidade())
                                                .dataCriacao(v.getTurista().getDataCriacao())
                                                .dataEdicao(v.getTurista().getDataEdicao())
                                                .dataNascimento(v.getTurista().getDataNascimento())
                                                .estado(v.getTurista().getEstado())
                                                .id(v.getTurista().getId())
                                                .nome(v.getTurista().getNome())
                                                .numeroCasa(v.getTurista().getNumeroCasa())
                                                .profissao(v.getTurista().getProfissao())
                                                .registroGeral(v.getTurista().getRegistroGeral())
                                                .rua(v.getTurista().getRua())
                                                .telefone(v.getTurista().getTelefone())
                                                .build())
                                .build()

                ).collect(Collectors.toList());
        }

        @Override
        @Transactional
        public List<DadosVoucherDTO> getVouchersDoTurista(Long id) {

                Turista turista = turistaRepository.findById(id)
                                .orElseThrow(() -> new RegraNegocioException(TURISTA_NAO_ENCONTRADO));

                return voucherRepository.findAllByTurista(turista).stream().map((Voucher v) ->

                DadosVoucherDTO.builder()
                                .id(v.getId())
                                .codigo(v.getCodigo())
                                .valor(v.getValor())
                                .dataCriacao(v.getDataCriacao())
                                .dataEdicao(v.getDataEdicao())
                                .turismo(TurismoDTO.builder()
                                                .bairro(v.getTurismo().getBairro())
                                                .cadastroNacionalPessoasJuridicas(
                                                                v.getTurismo().getCadastroNacionalPessoasJuridicas())
                                                .categoriaId(v.getTurismo().getCategoria().getId())
                                                .cidade(v.getTurismo().getCidade())
                                                .dataCriacao(v.getTurismo().getDataCriacao())
                                                .dataEdicao(v.getTurismo().getDataEdicao())
                                                .descricao(v.getTurismo().getDescricao())
                                                .estado(v.getTurismo().getEstado())
                                                .id(v.getTurismo().getId())
                                                .nome(v.getTurismo().getNome())
                                                .numeroLocal(v.getTurismo().getNumeroLocal())
                                                .rua(v.getTurismo().getRua())
                                                .telefone(v.getTurismo().getTelefone())
                                                .turistaId(v.getTurismo().getTurista().getId())
                                                .build())
                                .turista(TuristaDTO.builder()
                                                .bairro(v.getTurista().getBairro())
                                                .cadastroPessoaFisica(v.getTurista()
                                                                .getCadastroPessoaFisica())
                                                .cidade(v.getTurista().getCidade())
                                                .dataCriacao(v.getTurista().getDataCriacao())
                                                .dataEdicao(v.getTurista().getDataEdicao())
                                                .dataNascimento(v.getTurista().getDataNascimento())
                                                .estado(v.getTurista().getEstado())
                                                .id(v.getTurista().getId())
                                                .nome(v.getTurista().getNome())
                                                .numeroCasa(v.getTurista().getNumeroCasa())
                                                .profissao(v.getTurista().getProfissao())
                                                .registroGeral(v.getTurista().getRegistroGeral())
                                                .rua(v.getTurista().getRua())
                                                .telefone(v.getTurista().getTelefone())
                                                .build())
                                .build()

                ).collect(Collectors.toList());
        }

        @Override
        @Transactional
        public DadosVoucherDTO getById(Long id) {

                return voucherRepository.findById(id).map((Voucher v) -> {

                        if (v.getTurista() != null)
                                return DadosVoucherDTO.builder()
                                                .id(v.getId())
                                                .codigo(v.getCodigo())
                                                .valor(v.getValor())
                                                .dataCriacao(v.getDataCriacao())
                                                .dataEdicao(v.getDataEdicao())
                                                .turismo(TurismoDTO.builder()
                                                                .bairro(v.getTurismo().getBairro())
                                                                .cadastroNacionalPessoasJuridicas(v.getTurismo()
                                                                                .getCadastroNacionalPessoasJuridicas())
                                                                .categoriaId(v.getTurismo().getCategoria().getId())
                                                                .cidade(v.getTurismo().getCidade())
                                                                .dataCriacao(v.getTurismo().getDataCriacao())
                                                                .dataEdicao(v.getTurismo().getDataEdicao())
                                                                .descricao(v.getTurismo().getDescricao())
                                                                .estado(v.getTurismo().getEstado())
                                                                .id(v.getTurismo().getId())
                                                                .nome(v.getTurismo().getNome())
                                                                .numeroLocal(v.getTurismo().getNumeroLocal())
                                                                .rua(v.getTurismo().getRua())
                                                                .telefone(v.getTurismo().getTelefone())
                                                                .turistaId(v.getTurismo().getTurista().getId())
                                                                .build())
                                                .turista(TuristaDTO.builder()
                                                                .bairro(v.getTurista().getBairro())
                                                                .cadastroPessoaFisica(v.getTurista()
                                                                                .getCadastroPessoaFisica())
                                                                .cidade(v.getTurista().getCidade())
                                                                .dataCriacao(v.getTurista().getDataCriacao())
                                                                .dataEdicao(v.getTurista().getDataEdicao())
                                                                .dataNascimento(v.getTurista().getDataNascimento())
                                                                .estado(v.getTurista().getEstado())
                                                                .id(v.getTurista().getId())
                                                                .nome(v.getTurista().getNome())
                                                                .numeroCasa(v.getTurista().getNumeroCasa())
                                                                .profissao(v.getTurista().getProfissao())
                                                                .registroGeral(v.getTurista().getRegistroGeral())
                                                                .rua(v.getTurista().getRua())
                                                                .telefone(v.getTurista().getTelefone())
                                                                .build())
                                                .build();

                        return DadosVoucherDTO.builder()
                                        .id(v.getId())
                                        .codigo(v.getCodigo())
                                        .valor(v.getValor())
                                        .dataCriacao(v.getDataCriacao())
                                        .dataEdicao(v.getDataEdicao())
                                        .turismo(TurismoDTO.builder()
                                                        .bairro(v.getTurismo().getBairro())
                                                        .cadastroNacionalPessoasJuridicas(
                                                                        v.getTurismo().getCadastroNacionalPessoasJuridicas())
                                                        .categoriaId(v.getTurismo().getCategoria().getId())
                                                        .cidade(v.getTurismo().getCidade())
                                                        .dataCriacao(v.getTurismo().getDataCriacao())
                                                        .dataEdicao(v.getTurismo().getDataEdicao())
                                                        .descricao(v.getTurismo().getDescricao())
                                                        .estado(v.getTurismo().getEstado())
                                                        .id(v.getTurismo().getId())
                                                        .nome(v.getTurismo().getNome())
                                                        .numeroLocal(v.getTurismo().getNumeroLocal())
                                                        .rua(v.getTurismo().getRua())
                                                        .telefone(v.getTurismo().getTelefone())
                                                        .turistaId(v.getTurismo().getTurista().getId())
                                                        .build())
                                        .build();

                }).orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));
        }

        @Override
        @Transactional
        public DadosVoucherDTO claim(VoucherDTO dto) {

                Voucher voucher = voucherRepository.findById(dto.getId())
                                .orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));

                Turismo turi = turismoRepository.findById(voucher.getTurismo().getId())
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                Turista turista = turistaRepository.findById(dto.getTuristaId())
                                .orElseThrow(() -> new RegraNegocioException(TURISTA_NAO_ENCONTRADO));

                if (voucher.getTurista() != null) {
                        throw new RegraNegocioException("Voucher expirado.");
                }

                voucher.setDataEdicao(dto.getDataEdicao());
                voucher.setTurismo(turi);
                voucher.setTurista(turista);

                voucherRepository.save(voucher);

                return voucherRepository.findById(dto.getId()).map((Voucher v) -> DadosVoucherDTO.builder()
                                .id(v.getId())
                                .codigo(v.getCodigo())
                                .valor(v.getValor())
                                .dataCriacao(v.getDataCriacao())
                                .dataEdicao(v.getDataEdicao())
                                .turismo(TurismoDTO.builder()
                                                .bairro(v.getTurismo().getBairro())
                                                .cadastroNacionalPessoasJuridicas(v.getTurismo()
                                                                .getCadastroNacionalPessoasJuridicas())
                                                .categoriaId(v.getTurismo().getCategoria().getId())
                                                .cidade(v.getTurismo().getCidade())
                                                .dataCriacao(v.getTurismo().getDataCriacao())
                                                .dataEdicao(v.getTurismo().getDataEdicao())
                                                .descricao(v.getTurismo().getDescricao())
                                                .estado(v.getTurismo().getEstado())
                                                .id(v.getTurismo().getId())
                                                .nome(v.getTurismo().getNome())
                                                .numeroLocal(v.getTurismo().getNumeroLocal())
                                                .rua(v.getTurismo().getRua())
                                                .telefone(v.getTurismo().getTelefone())
                                                .turistaId(v.getTurismo().getTurista().getId())
                                                .build())
                                .turista(TuristaDTO.builder()
                                                .bairro(v.getTurista().getBairro())
                                                .cadastroPessoaFisica(v.getTurista()
                                                                .getCadastroPessoaFisica())
                                                .cidade(v.getTurista().getCidade())
                                                .dataCriacao(v.getTurista().getDataCriacao())
                                                .dataEdicao(v.getTurista().getDataEdicao())
                                                .dataNascimento(v.getTurista().getDataNascimento())
                                                .estado(v.getTurista().getEstado())
                                                .id(v.getTurista().getId())
                                                .nome(v.getTurista().getNome())
                                                .numeroCasa(v.getTurista().getNumeroCasa())
                                                .profissao(v.getTurista().getProfissao())
                                                .registroGeral(v.getTurista().getRegistroGeral())
                                                .rua(v.getTurista().getRua())
                                                .telefone(v.getTurista().getTelefone())
                                                .build())
                                .build()).orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));

        }

        @Override
        @Transactional
        public void put(Long id, VoucherDTO dto) {

                // TODO
                Turismo turi = turismoRepository.findById(dto.getTurismoId())
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                Turista turista = turistaRepository.findById(dto.getTuristaId())
                                .orElseThrow(() -> new RegraNegocioException(TURISTA_NAO_ENCONTRADO));

                Voucher voucher = voucherRepository.findById(id)
                                .orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));

                voucher.setCodigo(dto.getCodigo());
                voucher.setValor(dto.getValor());
                voucher.setDataCriacao(dto.getDataCriacao());
                voucher.setDataEdicao(dto.getDataEdicao());
                voucher.setTurismo(turi);
                voucher.setTurista(turista);

                voucherRepository.save(voucher);

        }

        @Override
        @Transactional
        public void delete(Long id) {
                voucherRepository.deleteById(id);
        }

}
