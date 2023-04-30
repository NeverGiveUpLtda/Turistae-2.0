package com.api.turistae.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.api.turistae.dtos.DadosUsuarioDTO;
import com.api.turistae.dtos.UsuarioDTO;
import com.api.turistae.models.Usuario;
import com.api.turistae.repositorys.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    // Atributos
    private final UsuarioRepository usuarioRepository;

    // Métodos
    @Override
    public Long post(UsuarioDTO dto) {

        Usuario usu = new Usuario();
        usu.setNomeUsuario(dto.getNomeUsuario());
        usu.setSenha(dto.getSenha());
        usu.setNome(dto.getNome());
        usu.setEmail(dto.getEmail());
        usu.setTelefone(dto.getTelefone());
        usu.setNumeroCasa(dto.getNumeroCasa());
        usu.setRua(dto.getRua());
        usu.setBairro(dto.getBairro());
        usu.setCidade(dto.getCidade());
        usu.setEstado(dto.getEstado());
        usu.setDataNascimento(dto.getDataNascimento());
        usu.setProfissao(dto.getProfissao());
        usu.setCadastroPessoaFisica(dto.getCadastroPessoaFisica());
        usu.setRegistroGeral(dto.getRegistroGeral());
        usu.setDataEdicao(dataAtualMascara("yyyy-MM-dd-HH-mm-ss"));

        Usuario usuarioGerado = usuarioRepository.save(usu);

        return usuarioGerado.getId();

    }

    @Override
    public List<DadosUsuarioDTO> getAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAll'");
    }

    @Override
    public DadosUsuarioDTO getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    public void put(Long id, UsuarioDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'put'");
    }

    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    private LocalDateTime dataAtualMascara(String mascara) {

        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern(mascara));
        return LocalDateTime.parse(data, DateTimeFormatter.ofPattern(mascara));

    }

}
