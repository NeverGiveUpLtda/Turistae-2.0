package com.api.turistae.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.turistae.dtos.DadosImagemDTO;
import com.api.turistae.dtos.ImagemDTO;
import com.api.turistae.dtos.TurismoDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Imagem;
import com.api.turistae.models.Turismo;
import com.api.turistae.repositorys.ImagemRepository;
import com.api.turistae.repositorys.TurismoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImagemServiceImpl implements ImagemService {

    // Atributos
    private final TurismoRepository turismoRepository;
    private final ImagemRepository imagemRepository;

    /**
     * Mude a mensagem de turismo não encontrado para imagem
     */
    private static final String TURISMO_NAO_ENCONTRADO = "Turismo não encontrado.";

    /**
     * Mude a mensagem de não encontrado para imagem
     */
    private static final String NAO_ENCONTRADO = "Imagem não encontrada.";

    // Métodos
    @Override
    @Transactional
    public Long post(ImagemDTO dto) {

        Turismo turis = turismoRepository
                .findById(dto.getTurismoId())
                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

        Imagem imagem = new Imagem();

        imagem.setDataCriacao(dto.getDataCriacao());
        imagem.setDataEdicao(dto.getDataEdicao());
        imagem.setString64(dto.getString64());
        imagem.setTurismo(turis);

        Imagem imagemGerada;

        imagemGerada = imagemRepository.save(imagem);

        return imagemGerada.getId();

    }

    @Override
    @Transactional
    public List<DadosImagemDTO> getAll() {

        return imagemRepository.findAll().stream().map((Imagem i) -> DadosImagemDTO.builder()
                .id(i.getId())
                .dataCriacao(i.getDataCriacao())
                .dataEdicao(i.getDataEdicao())
                .string64(i.getString64())
                .turismo(TurismoDTO.builder()
                        .id(i.getTurismo().getId())
                        .bairro(i.getTurismo().getBairro())
                        .cadastroNacionalPessoasJuridicas(i.getTurismo().getCadastroNacionalPessoasJuridicas())
                        .categoriaId(i.getTurismo().getCategoria().getId())
                        .cidade(i.getTurismo().getCidade())
                        .dataCriacao(i.getTurismo().getDataCriacao())
                        .dataEdicao(i.getTurismo().getDataEdicao())
                        .descricao(i.getTurismo().getDescricao())
                        .estado(i.getTurismo().getEstado())
                        .nome(i.getTurismo().getNome())
                        .numeroLocal(i.getTurismo().getNumeroLocal())
                        .rua(i.getTurismo().getRua())
                        .telefone(i.getTurismo().getTelefone())
                        .turistaId(i.getTurismo().getTurista().getId())
                        .build())
                .build()).collect(Collectors.toList());

    }
    @Override
    @Transactional
    public List<DadosImagemDTO> getByTurismo(Long id) {

        Turismo turis = turismoRepository
                .findById(id)
                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

        return imagemRepository.findAllByTurismo(turis).stream().map((Imagem i) -> DadosImagemDTO.builder()
                .id(i.getId())
                .dataCriacao(i.getDataCriacao())
                .dataEdicao(i.getDataEdicao())
                .string64(i.getString64())
                .turismo(TurismoDTO.builder()
                        .id(i.getTurismo().getId())
                        .bairro(i.getTurismo().getBairro())
                        .cadastroNacionalPessoasJuridicas(i.getTurismo().getCadastroNacionalPessoasJuridicas())
                        .categoriaId(i.getTurismo().getCategoria().getId())
                        .cidade(i.getTurismo().getCidade())
                        .dataCriacao(i.getTurismo().getDataCriacao())
                        .dataEdicao(i.getTurismo().getDataEdicao())
                        .descricao(i.getTurismo().getDescricao())
                        .estado(i.getTurismo().getEstado())
                        .nome(i.getTurismo().getNome())
                        .numeroLocal(i.getTurismo().getNumeroLocal())
                        .rua(i.getTurismo().getRua())
                        .telefone(i.getTurismo().getTelefone())
                        .turistaId(i.getTurismo().getTurista().getId())
                        .build())
                .build()).collect(Collectors.toList());

    }

    @Override
    @Transactional
    public DadosImagemDTO getById(Long id) {

        return imagemRepository.findById(id).map((Imagem i) -> DadosImagemDTO.builder()
                .id(i.getId())
                .dataCriacao(i.getDataCriacao())
                .dataEdicao(i.getDataEdicao())
                .string64(i.getString64()).turismo(TurismoDTO.builder()
                        .id(i.getTurismo().getId())
                        .bairro(i.getTurismo().getBairro())
                        .cadastroNacionalPessoasJuridicas(i.getTurismo().getCadastroNacionalPessoasJuridicas())
                        .categoriaId(i.getTurismo().getCategoria().getId())
                        .cidade(i.getTurismo().getCidade())
                        .dataCriacao(i.getTurismo().getDataCriacao())
                        .dataEdicao(i.getTurismo().getDataEdicao())
                        .descricao(i.getTurismo().getDescricao())
                        .estado(i.getTurismo().getEstado())
                        .nome(i.getTurismo().getNome())
                        .numeroLocal(i.getTurismo().getNumeroLocal())
                        .rua(i.getTurismo().getRua())
                        .telefone(i.getTurismo().getTelefone())
                        .turistaId(i.getTurismo().getTurista().getId())
                        .build())
                .build()).orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));
    }

    @Override
    @Transactional
    public void put(Long id, ImagemDTO dto) {

        Imagem imagem = imagemRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));

        Turismo turis = turismoRepository.findById(dto.getTurismoId())
                .orElseThrow(() -> new RegraNegocioException(TURISMO_NAO_ENCONTRADO));

        imagem.setDataCriacao(dto.getDataCriacao());
        imagem.setDataEdicao(dto.getDataEdicao());
        imagem.setString64(dto.getString64());
        imagem.setTurismo(turis);

        imagemRepository.save(imagem);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        imagemRepository.deleteById(id);
    }

}
