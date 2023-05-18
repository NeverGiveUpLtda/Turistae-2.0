package com.api.turistae.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.turistae.dtos.DadosVoucherDTO;
import com.api.turistae.dtos.TurismoDTO;
import com.api.turistae.dtos.UsuarioDTO;
import com.api.turistae.dtos.VoucherDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Turismo;
import com.api.turistae.models.Usuario;
import com.api.turistae.models.Voucher;
import com.api.turistae.repositorys.TurismoRepository;
import com.api.turistae.repositorys.UsuarioRepository;
import com.api.turistae.repositorys.VoucherRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VoucherServiceImpl implements VoucherService {

    /**
     * Mude a mensagem de usuário não encontrado para curtida
     */
    private static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado.";

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
        private final UsuarioRepository usuarioRepository;

        @Override
        @Transactional
        public Long post(VoucherDTO dto) {

                Turismo turi = turismoRepository.findById(dto.getTurismoId())
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                Voucher voucher = new Voucher();

                voucher.setCodigo(dto.getCodigo());
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

                        if (v.getUsuario() != null)
                                return DadosVoucherDTO.builder()
                                                .id(v.getId())
                                                .codigo(v.getCodigo())
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
                                                                .usuarioId(v.getTurismo().getUsuario().getId())
                                                                .build())
                                                .usuario(UsuarioDTO.builder()
                                                                .bairro(v.getUsuario().getBairro())
                                                                .cadastroPessoaFisica(v.getUsuario()
                                                                                .getCadastroPessoaFisica())
                                                                .cidade(v.getUsuario().getCidade())
                                                                .dataCriacao(v.getUsuario().getDataCriacao())
                                                                .dataEdicao(v.getUsuario().getDataEdicao())
                                                                .dataNascimento(v.getUsuario().getDataNascimento())
                                                                .email(v.getUsuario().getEmail())
                                                                .estado(v.getUsuario().getEstado())
                                                                .id(v.getUsuario().getId())
                                                                .nome(v.getUsuario().getNome())
                                                                .nomeUsuario(v.getUsuario().getNomeUsuario())
                                                                .numeroCasa(v.getUsuario().getNumeroCasa())
                                                                .profissao(v.getUsuario().getProfissao())
                                                                .registroGeral(v.getUsuario().getRegistroGeral())
                                                                .rua(v.getUsuario().getRua())
                                                                .senha(v.getUsuario().getSenha())
                                                                .telefone(v.getUsuario().getTelefone())
                                                                .build())
                                                .build();
                        return DadosVoucherDTO.builder()
                                        .id(v.getId())
                                        .codigo(v.getCodigo())
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
                                                        .usuarioId(v.getTurismo().getUsuario().getId())
                                                        .build())
                                        .build();

                }).collect(Collectors.toList());
        }

        @Override
        @Transactional
        public DadosVoucherDTO getById(Long id) {

                return voucherRepository.findById(id).map((Voucher v) -> {

                        if (v.getUsuario() != null)
                                return DadosVoucherDTO.builder()
                                                .id(v.getId())
                                                .codigo(v.getCodigo())
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
                                                                .usuarioId(v.getTurismo().getUsuario().getId())
                                                                .build())
                                                .usuario(UsuarioDTO.builder()
                                                                .bairro(v.getUsuario().getBairro())
                                                                .cadastroPessoaFisica(v.getUsuario()
                                                                                .getCadastroPessoaFisica())
                                                                .cidade(v.getUsuario().getCidade())
                                                                .dataCriacao(v.getUsuario().getDataCriacao())
                                                                .dataEdicao(v.getUsuario().getDataEdicao())
                                                                .dataNascimento(v.getUsuario().getDataNascimento())
                                                                .email(v.getUsuario().getEmail())
                                                                .estado(v.getUsuario().getEstado())
                                                                .id(v.getUsuario().getId())
                                                                .nome(v.getUsuario().getNome())
                                                                .nomeUsuario(v.getUsuario().getNomeUsuario())
                                                                .numeroCasa(v.getUsuario().getNumeroCasa())
                                                                .profissao(v.getUsuario().getProfissao())
                                                                .registroGeral(v.getUsuario().getRegistroGeral())
                                                                .rua(v.getUsuario().getRua())
                                                                .senha(v.getUsuario().getSenha())
                                                                .telefone(v.getUsuario().getTelefone())
                                                                .build())
                                                .build();

                        return DadosVoucherDTO.builder()
                                        .id(v.getId())
                                        .codigo(v.getCodigo())
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
                                                        .usuarioId(v.getTurismo().getUsuario().getId())
                                                        .build())
                                        .build();

                }).orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));
        }

        @Override
        @Transactional
        public void put(Long id, VoucherDTO dto) {

                Turismo turi = turismoRepository.findById(dto.getTurismoId())
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                Usuario usu = usuarioRepository.findById(dto.getUsuarioId())
                                .orElseThrow(() -> new RegraNegocioException(USUARIO_NAO_ENCONTRADO));

                Voucher voucher = voucherRepository.findById(id)
                                .orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));

                voucher.setCodigo(dto.getCodigo());
                voucher.setDataCriacao(dto.getDataCriacao());
                voucher.setDataEdicao(dto.getDataEdicao());
                voucher.setTurismo(turi);
                voucher.setUsuario(usu);

                voucherRepository.save(voucher);

        }

        @Override
        @Transactional
        public void delete(Long id) {
                voucherRepository.deleteById(id);
        }

}
