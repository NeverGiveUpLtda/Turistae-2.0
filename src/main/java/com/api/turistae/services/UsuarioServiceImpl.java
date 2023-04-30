package com.api.turistae.services;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.api.turistae.dtos.DadosUsuarioDTO;
import com.api.turistae.dtos.UsuarioDTO;
import com.api.turistae.exceptions.CriptografiaException;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.models.Usuario;
import com.api.turistae.repositorys.UsuarioRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    // Atributos
    private final UsuarioRepository usuarioRepository;
    private final Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    // Métodos
    @Override
    @Transactional
    public Long post(UsuarioDTO dto) {

        logger.info("Post usuário: {}", dto.toString());

        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(dto.getNomeUsuario());

        try {
            usuario.setSenha(criptografarSenha(dto.getSenha()));
        } catch (CriptografiaException e) {
            throw new RegraNegocioException(e.getMessage());
        }

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setTelefone(dto.getTelefone());
        usuario.setNumeroCasa(dto.getNumeroCasa());
        usuario.setRua(dto.getRua());
        usuario.setBairro(dto.getBairro());
        usuario.setCidade(dto.getCidade());
        usuario.setEstado(dto.getEstado());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setProfissao(dto.getProfissao());
        usuario.setCadastroPessoaFisica(dto.getCadastroPessoaFisica());
        usuario.setRegistroGeral(dto.getRegistroGeral());
        usuario.setDataEdicao(getDataAtualComMascara("yyyy-MM-dd-HH-mm-ss"));

        Usuario usuarioGerado = usuarioRepository.save(usuario);

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
    private LocalDateTime getDataAtualComMascara(String mascara) {

        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern(mascara));
        return LocalDateTime.parse(data, DateTimeFormatter.ofPattern(mascara));

    }

    // TODO mudar esse método para o controller
    private String criptografarSenha(String senha) throws CriptografiaException {
        try {

            // Crie uma instância da classe MessageDigest com o algoritmo SHA-256
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Converta a senha para um array de bytes e realize a criptografia
            byte[] senhaCriptografada = messageDigest.digest(senha.getBytes());

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

            logger.error(e.getMessage(), e);

            // Trate o erro caso ocorra
            throw new CriptografiaException("Não foi possível Criptografar a senha");
        }
    }

}
