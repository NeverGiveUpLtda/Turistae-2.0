package com.api.turistae.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.turistae.dtos.CategoriaDto;
import com.api.turistae.dtos.DadosCategoriaDTO;
import com.api.turistae.models.Categoria;
import com.api.turistae.repositorys.CategoriaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    // Atributos
    private final CategoriaRepository categoriaRepository;

    // Métodos
    @Override
    public Long post(CategoriaDto dto) {

        Categoria categoria = new Categoria();

        categoria.setNome(dto.getNome());
        categoria.setDataEdicao(dto.getDataEdicao());

        Categoria categoriaGerada = categoriaRepository.save(categoria);

        return categoriaGerada.getId();

    }

    @Override
    public List<DadosCategoriaDTO> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public DadosCategoriaDTO getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public void put(Long id, CategoriaDto dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'put'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
