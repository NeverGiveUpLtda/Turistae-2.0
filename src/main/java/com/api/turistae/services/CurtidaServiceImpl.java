package com.api.turistae.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.turistae.dtos.CurtidaDTO;
import com.api.turistae.dtos.DadosCurtidaDTO;
import com.api.turistae.dtos.TurismoDTO;
import com.api.turistae.dtos.UsuarioDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Curtida;
import com.api.turistae.models.Turismo;
import com.api.turistae.models.Usuario;
import com.api.turistae.repositorys.CurtidaRepository;
import com.api.turistae.repositorys.TurismoRepository;
import com.api.turistae.repositorys.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurtidaServiceImpl implements CurtidaService {

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
        private static final String NAO_ENCONTRADO = "Curtida não encontrado.";

        // Atributos
        private final CurtidaRepository curtidaRepository;
        private final TurismoRepository turismoRepository;
        private final UsuarioRepository usuarioRepository;

        // Métodos
        @Override
        @Transactional
        public Long post(CurtidaDTO dto) {

                Turismo turis = turismoRepository
                                .findById(dto.getTurismoId())
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                Usuario usu = usuarioRepository
                                .findById(dto.getUsuarioId())
                                .orElseThrow(() -> new RegraNegocioException(USUARIO_NAO_ENCONTRADO));

                Curtida curtida = new Curtida();

                curtida.setDataCriacao(dto.getDataCriacao());
                curtida.setDataEdicao(dto.getDataEdicao());
                curtida.setTurismo(turis);
                curtida.setUsuario(usu);

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
                                                .cadastroNacionalPessoasJuridicas(
                                                                c.getTurismo().getCadastroNacionalPessoasJuridicas())
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
                                                .usuarioId(c.getTurismo().getUsuario().getId())
                                                .build())

                                // Relacionamentos
                                .usuario(UsuarioDTO.builder()
                                                .id(c.getUsuario().getId())
                                                .bairro(c.getUsuario().getBairro())
                                                .cadastroPessoaFisica(c.getUsuario().getCadastroPessoaFisica())
                                                .cidade(c.getUsuario().getCidade())
                                                .dataCriacao(c.getUsuario().getDataCriacao())
                                                .dataEdicao(c.getUsuario().getDataEdicao())
                                                .dataNascimento(c.getUsuario().getDataNascimento())
                                                .email(c.getUsuario().getEmail())
                                                .estado(c.getUsuario().getEstado())
                                                .nome(c.getUsuario().getNome())
                                                .nomeUsuario(c.getUsuario().getNomeUsuario())
                                                .numeroCasa(c.getUsuario().getNumeroCasa())
                                                .profissao(c.getUsuario().getProfissao())
                                                .registroGeral(c.getUsuario().getRegistroGeral())
                                                .rua(c.getUsuario().getRua())
                                                .senha(c.getUsuario().getSenha())
                                                .telefone(c.getUsuario().getTelefone())
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
                                                .cadastroNacionalPessoasJuridicas(
                                                                c.getTurismo().getCadastroNacionalPessoasJuridicas())
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
                                                .usuarioId(c.getTurismo().getUsuario().getId())
                                                .build())

                                // Relacionamentos
                                .usuario(UsuarioDTO.builder()
                                                .id(c.getUsuario().getId())
                                                .bairro(c.getUsuario().getBairro())
                                                .cadastroPessoaFisica(c.getUsuario().getCadastroPessoaFisica())
                                                .cidade(c.getUsuario().getCidade())
                                                .dataCriacao(c.getUsuario().getDataCriacao())
                                                .dataEdicao(c.getUsuario().getDataEdicao())
                                                .dataNascimento(c.getUsuario().getDataNascimento())
                                                .email(c.getUsuario().getEmail())
                                                .estado(c.getUsuario().getEstado())
                                                .nome(c.getUsuario().getNome())
                                                .nomeUsuario(c.getUsuario().getNomeUsuario())
                                                .numeroCasa(c.getUsuario().getNumeroCasa())
                                                .profissao(c.getUsuario().getProfissao())
                                                .registroGeral(c.getUsuario().getRegistroGeral())
                                                .rua(c.getUsuario().getRua())
                                                .senha(c.getUsuario().getSenha())
                                                .telefone(c.getUsuario().getTelefone())
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
                                                .cadastroNacionalPessoasJuridicas(
                                                                c.getTurismo().getCadastroNacionalPessoasJuridicas())
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
                                                .usuarioId(c.getTurismo().getUsuario().getId())
                                                .build())

                                // Relacionamentos
                                .usuario(UsuarioDTO.builder()
                                                .id(c.getUsuario().getId())
                                                .bairro(c.getUsuario().getBairro())
                                                .cadastroPessoaFisica(c.getUsuario().getCadastroPessoaFisica())
                                                .cidade(c.getUsuario().getCidade())
                                                .dataCriacao(c.getUsuario().getDataCriacao())
                                                .dataEdicao(c.getUsuario().getDataEdicao())
                                                .dataNascimento(c.getUsuario().getDataNascimento())
                                                .email(c.getUsuario().getEmail())
                                                .estado(c.getUsuario().getEstado())
                                                .nome(c.getUsuario().getNome())
                                                .nomeUsuario(c.getUsuario().getNomeUsuario())
                                                .numeroCasa(c.getUsuario().getNumeroCasa())
                                                .profissao(c.getUsuario().getProfissao())
                                                .registroGeral(c.getUsuario().getRegistroGeral())
                                                .rua(c.getUsuario().getRua())
                                                .senha(c.getUsuario().getSenha())
                                                .telefone(c.getUsuario().getTelefone())
                                                .build())
                                .build()).orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));
        }

        @Override
        @Transactional
        public DadosCurtidaDTO getCurtidaByTurismoAndUsuario(Long usuarioId, Long turismoId) {

                Turismo turis = turismoRepository
                                .findById(turismoId)
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                Usuario usu = usuarioRepository
                                .findById(usuarioId)
                                .orElseThrow(() -> new RegraNegocioException(USUARIO_NAO_ENCONTRADO));

                return curtidaRepository.findByUsuarioAndTurismo(usu, turis).map((Curtida c) -> DadosCurtidaDTO.builder()
                                .id(c.getId())
                                .dataCriacao(c.getDataCriacao())
                                .dataEdicao(c.getDataEdicao())
                                .turismo(TurismoDTO.builder()
                                                .id(c.getTurismo().getId())
                                                .bairro(c.getTurismo().getBairro())
                                                .cadastroNacionalPessoasJuridicas(
                                                                c.getTurismo().getCadastroNacionalPessoasJuridicas())
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
                                                .usuarioId(c.getTurismo().getUsuario().getId())
                                                .build())

                                // Relacionamentos
                                .usuario(UsuarioDTO.builder()
                                                .id(c.getUsuario().getId())
                                                .bairro(c.getUsuario().getBairro())
                                                .cadastroPessoaFisica(c.getUsuario().getCadastroPessoaFisica())
                                                .cidade(c.getUsuario().getCidade())
                                                .dataCriacao(c.getUsuario().getDataCriacao())
                                                .dataEdicao(c.getUsuario().getDataEdicao())
                                                .dataNascimento(c.getUsuario().getDataNascimento())
                                                .email(c.getUsuario().getEmail())
                                                .estado(c.getUsuario().getEstado())
                                                .nome(c.getUsuario().getNome())
                                                .nomeUsuario(c.getUsuario().getNomeUsuario())
                                                .numeroCasa(c.getUsuario().getNumeroCasa())
                                                .profissao(c.getUsuario().getProfissao())
                                                .registroGeral(c.getUsuario().getRegistroGeral())
                                                .rua(c.getUsuario().getRua())
                                                .senha(c.getUsuario().getSenha())
                                                .telefone(c.getUsuario().getTelefone())
                                                .build())
                                .build()).orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));

        }

        @Override
        @Transactional
        public void put(Long id, CurtidaDTO dto) {

                Turismo turi = turismoRepository.findById(dto.getTurismoId())
                                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

                Usuario usu = usuarioRepository.findById(dto.getUsuarioId())
                                .orElseThrow(() -> new RegraNegocioException(USUARIO_NAO_ENCONTRADO));

                Curtida curtida = curtidaRepository.findById(id)
                                .orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));

                curtida.setDataCriacao(dto.getDataCriacao());
                curtida.setDataEdicao(dto.getDataEdicao());
                curtida.setId(dto.getId());
                curtida.setTurismo(turi);
                curtida.setUsuario(usu);

                curtidaRepository.save(curtida);

        }

        @Override
        @Transactional
        public void delete(Long id) {
                curtidaRepository.deleteById(id);
        }

}
