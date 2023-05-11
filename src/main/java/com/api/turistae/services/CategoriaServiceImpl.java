package com.api.turistae.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.turistae.dtos.CategoriaDTO;
import com.api.turistae.dtos.TurismoDTO;
import com.api.turistae.dtos.DadosCategoriaDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Categoria;
import com.api.turistae.models.Turismo;
import com.api.turistae.repositorys.CategoriaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    // Atributos
    private final CategoriaRepository categoriaRepository;

    // Métodos
    @Override
    @Transactional
    public Long post(CategoriaDTO dto) {

        Categoria categoria = new Categoria();

        categoria.setNome(dto.getNome());
        categoria.setDataCriacao(dto.getDataCriacao());
        categoria.setDataEdicao(dto.getDataEdicao());

        Categoria categoriaGerada;

        categoriaGerada = categoriaRepository.save(categoria);

        return categoriaGerada.getId();
    }

    @Override
    @Transactional
    public List<DadosCategoriaDTO> getAll() {

        return categoriaRepository.findAll()
                .stream()
                .map((Categoria c) -> DadosCategoriaDTO.builder()
                        .id(c.getId())
                        .nome(c.getNome())
                        .dataCriacao(c.getDataCriacao())
                        .dataEdicao(c.getDataEdicao())

                        // Relacionamentos
                        .turismos(c.getTurismos().stream().map((Turismo t) -> TurismoDTO.builder()
                                .id(t.getId())
                                .bairro(t.getBairro())
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
    public DadosCategoriaDTO getById(Long id) {

        return categoriaRepository.findById(id).map((Categoria c) -> DadosCategoriaDTO.builder()
                .id(c.getId())
                .nome(c.getNome())
                .dataCriacao(c.getDataCriacao())
                .dataEdicao(c.getDataEdicao())

                // Relacionamentos
                .turismos(c.getTurismos().stream().map((Turismo t) -> TurismoDTO.builder()
                        .id(t.getId())
                        .bairro(t.getBairro())
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
                .build()).orElseThrow(() -> new RegraNegocioException("Categoria não encontrada."));

    }

    @Override
    @Transactional
    public void put(Long id, CategoriaDTO dto) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Categoria não econtrada."));

        categoria.setNome(dto.getNome());
        categoria.setDataCriacao(dto.getDataCriacao());
        categoria.setDataEdicao(dto.getDataEdicao());

        categoriaRepository.save(categoria);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        categoriaRepository.deleteById(id);
    }

}
