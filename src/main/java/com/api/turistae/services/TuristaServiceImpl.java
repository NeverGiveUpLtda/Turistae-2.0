package com.api.turistae.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.turistae.dtos.CurtidaDTO;
import com.api.turistae.dtos.DadosTuristaDTO;
import com.api.turistae.dtos.ReviewDTO;
import com.api.turistae.dtos.TurismoDTO;
import com.api.turistae.dtos.TuristaDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Curtida;
import com.api.turistae.models.Review;
import com.api.turistae.models.Turista;
import com.api.turistae.models.Turismo;
import com.api.turistae.repositorys.TuristaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TuristaServiceImpl implements TuristaService {

    /**
     * Mude a mensagem de não encontrado para turista
     */
    private static final String NAO_ENCONTRADO = "Turista não encontrado.";


    // Atributos
    private final TuristaRepository turistaRepository;

    // Métodos
    @Override
    @Transactional
    public Long post(TuristaDTO dto) {

        Turista turista = new Turista();

        turista.setNome(dto.getNome());
        turista.setTelefone(dto.getTelefone());
        turista.setNumeroCasa(dto.getNumeroCasa());
        turista.setRua(dto.getRua());
        turista.setBairro(dto.getBairro());
        turista.setCidade(dto.getCidade());
        turista.setEstado(dto.getEstado());
        turista.setDataNascimento(dto.getDataNascimento());
        turista.setProfissao(dto.getProfissao());
        turista.setCadastroPessoaFisica(dto.getCadastroPessoaFisica());
        turista.setRegistroGeral(dto.getRegistroGeral());
        turista.setDataCriacao(dto.getDataCriacao());
        turista.setDataEdicao(dto.getDataEdicao());

        Turista turistaGerado;

        turistaGerado = turistaRepository.save(turista);

        return turistaGerado.getId();

    }

    @Override
    @Transactional
    public List<DadosTuristaDTO> getAll() {

        return turistaRepository.findAll()
                .stream()
                .map((Turista u) -> DadosTuristaDTO.builder()
                        .id(u.getId())
                        .bairro(u.getBairro())
                        .cadastroPessoaFisica(u.getCadastroPessoaFisica())
                        .cidade(u.getCidade())
                        .dataCriacao(u.getDataCriacao())
                        .dataEdicao(u.getDataEdicao())
                        .dataNascimento(u.getDataNascimento())
                        .estado(u.getEstado())
                        .bairro(u.getBairro())
                        .nome(u.getNome())
                        .numeroCasa(u.getNumeroCasa())
                        .profissao(u.getProfissao())
                        .registroGeral(u.getRegistroGeral())
                        .rua(u.getRua())
                        .telefone(u.getTelefone())
                        .dataCriacao(u.getDataCriacao())
                        .dataEdicao(u.getDataEdicao())

                        // Relacionamentos
                        .curtidas(u.getCurtidas().stream().map((Curtida c) -> CurtidaDTO.builder()
                                .id(c.getId())
                                .turismoId(c.getTurismo().getId())
                                .turistaId(c.getTurista().getId())
                                .dataCriacao(c.getDataCriacao())
                                .dataEdicao(c.getDataEdicao())
                                .build()).collect(Collectors.toList()))
                        .reviews(u.getReviews().stream().map((Review r) -> ReviewDTO.builder()
                                .id(r.getId())
                                .dataCriacao(r.getDataCriacao())
                                .dataEdicao(r.getDataEdicao())
                                .nota(r.getNota())
                                .texto(r.getTexto())
                                .turismoId(r.getTurismo().getId())
                                .turistaId(r.getTurista().getId())
                                .build()).collect(Collectors.toList()))
                        .turismos(u.getTurismos().stream().map((Turismo t) -> TurismoDTO.builder()
                                .id(t.getId())
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
                                .turistaId(t.getTurista().getId())
                                .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public DadosTuristaDTO getById(Long id) {
        return turistaRepository.findById(id).map((Turista u) -> DadosTuristaDTO.builder()
                .id(u.getId())
                .bairro(u.getBairro())
                .cadastroPessoaFisica(u.getCadastroPessoaFisica())
                .cidade(u.getCidade())
                .dataCriacao(u.getDataCriacao())
                .dataEdicao(u.getDataEdicao())
                .dataNascimento(u.getDataNascimento())
                .estado(u.getEstado())
                .nome(u.getNome())
                .numeroCasa(u.getNumeroCasa())
                .profissao(u.getProfissao())
                .registroGeral(u.getRegistroGeral())
                .rua(u.getRua())
                .telefone(u.getTelefone())
                .dataCriacao(u.getDataCriacao())
                .dataEdicao(u.getDataEdicao())

                // Relacionamentos
                .curtidas(u.getCurtidas().stream().map((Curtida c) -> CurtidaDTO.builder()
                        .id(c.getId())
                        .dataCriacao(c.getDataCriacao())
                        .dataEdicao(c.getDataEdicao())
                        .turismoId(c.getTurismo().getId())
                        .turistaId(c.getTurista().getId())
                        .build()).collect(Collectors.toList()))
                .reviews(u.getReviews().stream().map((Review r) -> ReviewDTO.builder()
                        .id(r.getId())
                        .dataCriacao(r.getDataCriacao())
                        .dataEdicao(r.getDataEdicao())
                        .nota(r.getNota())
                        .texto(r.getTexto())
                        .turismoId(r.getTurismo().getId())
                        .turistaId(r.getTurista().getId())
                        .build()).collect(Collectors.toList()))
                .turismos(u.getTurismos().stream().map((Turismo t) -> TurismoDTO.builder()
                        .id(t.getId())
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
                        .turistaId(t.getTurista().getId())
                        .build()).collect(Collectors.toList()))
                .build()).orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));
    }

    @Override
    @Transactional
    public void put(Long id, TuristaDTO dto) {

        Turista turista = turistaRepository.findById(id)
                .orElseThrow(() -> new RegraNegocioException(NAO_ENCONTRADO));

        turista.setNome(dto.getNome());
        turista.setTelefone(dto.getTelefone());
        turista.setNumeroCasa(dto.getNumeroCasa());
        turista.setRua(dto.getRua());
        turista.setBairro(dto.getBairro());
        turista.setCidade(dto.getCidade());
        turista.setEstado(dto.getEstado());
        turista.setDataNascimento(dto.getDataNascimento());
        turista.setProfissao(dto.getProfissao());
        turista.setCadastroPessoaFisica(dto.getCadastroPessoaFisica());
        turista.setRegistroGeral(dto.getRegistroGeral());
        turista.setDataCriacao(dto.getDataCriacao());
        turista.setDataEdicao(dto.getDataEdicao());

        turistaRepository.save(turista);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        turistaRepository.deleteById(id);
    }

}
