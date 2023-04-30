package com.api.turistae.services;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.turistae.dtos.DadosUsuarioDTO;
import com.api.turistae.dtos.UsuarioDTO;
import com.api.turistae.exceptions.CriptografiaException;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Usuario;
import com.api.turistae.repositorys.CurtidaRepository;
import com.api.turistae.repositorys.ReviewRepository;
import com.api.turistae.repositorys.TurismoRepository;
import com.api.turistae.repositorys.UsuarioRepository;
import com.api.turistae.repositorys.VoucherRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    // Atributos
    private final UsuarioRepository usuarioRepository;
    private final TurismoRepository turismoRepository;
    private final CurtidaRepository curtidaRepository;
    private final ReviewRepository reviewRepository;
    private final VoucherRepository voucherRepository;

    // Métodos
    @Override
    @Transactional
    public Long post(UsuarioDTO dto) {

        Usuario usu = new Usuario();
        usu.setNomeUsuario(dto.getNomeUsuario());
        try {
            usu.setSenha(criptografarSenha(dto.getSenha()));
        } catch (CriptografiaException e) {
            throw new RegraNegocioException(e.getMessage());
        }

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
    @Transactional
    public List<DadosUsuarioDTO> getAll() {

        return usuarioRepository.findAll()
                .stream()
                .map((Usuario u) -> DadosUsuarioDTO.builder()
                        .id(u.getId())
                        .cadastroPessoaFisica(u.getCadastroPessoaFisica())
                        .cidade(u.getCidade())
                        .curtidas(u.getCurtidas())
                        .dataCriacao(u.getDataCriacao())
                        .dataEdicao(u.getDataEdicao())
                        .dataNascimento(u.getDataNascimento())
                        .email(u.getEmail())
                        .estado(u.getEstado())
                        .nome(u.getNome())
                        .nomeUsuario(u.getNomeUsuario())
                        .numeroCasa(u.getNumeroCasa())
                        .profissao(u.getProfissao())
                        .registroGeral(u.getRegistroGeral())
                        .reviews(u.getReviews())
                        .rua(u.getRua())
                        .senha(u.getSenha())
                        .telefone(u.getTelefone())
                        .turismos(u.getTurismos())
                        .vouchers(u.getVouchers())
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public DadosUsuarioDTO getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

    @Override
    @Transactional
    public void put(Long id, UsuarioDTO dto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'put'");
    }

    @Override
    @Transactional
    public void delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    // Funcção que retorna a data atual com uma máscara
    private LocalDateTime dataAtualMascara(String mascara) {

        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern(mascara));
        return LocalDateTime.parse(data, DateTimeFormatter.ofPattern(mascara));

    }

    private String criptografarSenha(String senha) throws CriptografiaException {
        try {

            // Crie uma instância da classe MessageDigest com o algoritmo SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Converta a senha para um array de bytes e realize a criptografia
            byte[] senhaCriptografada = md.digest(senha.getBytes());

            // Converta o array de bytes para uma string hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < senhaCriptografada.length; i++) {
                String hex = Integer.toHexString(0xff & senhaCriptografada[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception e) {

            // Trate o erro caso ocorra
            throw new CriptografiaException("Não foi possível Criptografar a senha");
        }
    }

}
