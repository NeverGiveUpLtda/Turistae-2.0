package com.api.turistae.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.api.turistae.models.Curtida;
import com.api.turistae.models.Review;
import com.api.turistae.models.Turismo;
import com.api.turistae.models.Voucher;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosUsuarioDTO {
        
    //  Atributos
    private long id;
    private String nomeUsuario;
    private String senha;    
    private String nome;
    private String email;
    private long telefone;
    private int numeroCasa;
    private String rua;
    private String bairro;    
    private String cidade;
    private String estado;
    private LocalDateTime dataNascimento;
    private String profissao;    
    private String cadastroPessoaFisica;    
    private String registroGeral;

    //  Relacionamentos
    private List<Turismo> turismos;
    private List<Review> reviews;
    private List<Voucher> vouchers;
    private List<Curtida> curtidas;

    //  Timestamps
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;

}
