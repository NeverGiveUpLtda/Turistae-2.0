package com.api.turistae.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.turistae.dtos.CategoriaDTO;
import com.api.turistae.dtos.DadosTurismoDTO;
import com.api.turistae.dtos.TurismoDTO;
import com.api.turistae.dtos.UsuarioDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Categoria;
import com.api.turistae.models.Turismo;
import com.api.turistae.models.Usuario;
import com.api.turistae.repositorys.CategoriaRepository;
import com.api.turistae.repositorys.TurismoRepository;
import com.api.turistae.repositorys.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TurismoServiceImpl implements TurismoService {

    // Atributos
    private final TurismoRepository turismoRepository;
    private final CategoriaRepository categoriaRepository;
    private final UsuarioRepository usuarioRepository;

    // Métodos
    @Override
    @Transactional
    public Long post(TurismoDTO dto) {

        Categoria categ = categoriaRepository
                .findById(dto.getCategoriaId())
                .orElseThrow(() -> new RegraNegocioException("Categoria não encontrada."));

        Usuario usu = usuarioRepository
                .findById(dto.getUsuarioId())
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado"));

        Turismo turismo = new Turismo();

        turismo.setBairro(dto.getBairro());
        turismo.setCadastroNacionalPessoasJuridicas(dto.getCadastroNacionalPessoasJuridicas());
        turismo.setCidade(dto.getCidade());
        turismo.setDataCriacao(dto.getDataCriacao());
        turismo.setDataEdicao(dto.getDataEdicao());
        turismo.setDescricao(dto.getDescricao());
        turismo.setEstado(dto.getEstado());
        turismo.setNome(dto.getNome());
        turismo.setNumeroLocal(dto.getNumeroLocal());
        turismo.setRua(dto.getRua());
        turismo.setTelefone(dto.getTelefone());
        turismo.setUsuario(usu);
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
                .usuario(UsuarioDTO.builder()
                        .id(t.getUsuario().getId())
                        .bairro(t.getUsuario().getBairro())
                        .cadastroPessoaFisica(t.getUsuario().getCadastroPessoaFisica())
                        .cidade(t.getUsuario().getCidade())
                        .dataCriacao(t.getUsuario().getDataCriacao())
                        .dataEdicao(t.getUsuario().getDataEdicao())
                        .dataNascimento(t.getUsuario().getDataNascimento())
                        .email(t.getUsuario().getEmail())
                        .estado(t.getUsuario().getEstado())
                        .nome(t.getUsuario().getNome())
                        .nomeUsuario(t.getUsuario().getNomeUsuario())
                        .numeroCasa(t.getUsuario().getNumeroCasa())
                        .profissao(t.getUsuario().getProfissao())
                        .registroGeral(t.getUsuario().getRegistroGeral())
                        .rua(t.getUsuario().getRua())
                        .senha(t.getUsuario().getSenha())
                        .telefone(t.getUsuario().getTelefone())
                        .build())
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
                .usuario(UsuarioDTO.builder()
                        .id(t.getUsuario().getId())
                        .bairro(t.getUsuario().getBairro())
                        .cadastroPessoaFisica(t.getUsuario().getCadastroPessoaFisica())
                        .cidade(t.getUsuario().getCidade())
                        .dataCriacao(t.getUsuario().getDataCriacao())
                        .dataEdicao(t.getUsuario().getDataEdicao())
                        .dataNascimento(t.getUsuario().getDataNascimento())
                        .email(t.getUsuario().getEmail())
                        .estado(t.getUsuario().getEstado())
                        .nome(t.getUsuario().getNome())
                        .nomeUsuario(t.getUsuario().getNomeUsuario())
                        .numeroCasa(t.getUsuario().getNumeroCasa())
                        .profissao(t.getUsuario().getProfissao())
                        .registroGeral(t.getUsuario().getRegistroGeral())
                        .rua(t.getUsuario().getRua())
                        .senha(t.getUsuario().getSenha())
                        .telefone(t.getUsuario().getTelefone())
                        .build())
                .build()).orElseThrow(() -> new RegraNegocioException("Turismo não encontrado."));
    }

    @Override
    @Transactional
    public void put(Long id, TurismoDTO dto) {

        Turismo turismo = turismoRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Turismo não encontrado."));

        Categoria categ = categoriaRepository.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RegraNegocioException("Categoria não encontrada."));

        Usuario usu = usuarioRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado."));

        turismo.setBairro(dto.getBairro());
        turismo.setCadastroNacionalPessoasJuridicas(dto.getCadastroNacionalPessoasJuridicas());
        turismo.setCidade(dto.getCidade());
        turismo.setDataCriacao(dto.getDataCriacao());
        turismo.setDataEdicao(dto.getDataEdicao());
        turismo.setDescricao(dto.getDescricao());
        turismo.setEstado(dto.getEstado());
        turismo.setNome(dto.getNome());
        turismo.setNumeroLocal(dto.getNumeroLocal());
        turismo.setRua(dto.getRua());
        turismo.setTelefone(dto.getTelefone());
        turismo.setUsuario(usu);
        turismo.setCategoria(categ);

        turismoRepository.save(turismo);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        turismoRepository.deleteById(id);
    }

}
