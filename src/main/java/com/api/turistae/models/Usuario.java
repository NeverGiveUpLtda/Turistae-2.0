package com.api.turistae.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    //  Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 25, nullable = false)
    private String nomeUsuario;

    @Column(length = 20, nullable = false)
    private String senha;
    
    @Column(length = 200, nullable = false)
    private String nome;

    @Column(length = 200, nullable = false)
    private String email;

    @Column(nullable = false)
    private long telefone;

    @Column(nullable = false)
    private int numeroCasa;

    @Column(length = 200, nullable = false)
    private String rua;

    @Column(length = 200, nullable = false)
    private String bairro;
    
    @Column(length = 200, nullable = false)
    private String cidade;

    @Column(length = 200, nullable = false)
    private String estado;

    @Column(nullable = false)
    private LocalDateTime dataNascimento;

    @Column(length = 200, nullable = false)
    private String profissao;
    
    @Column(length = 14, nullable = false)
    private String cadastroPessoaFisica;
    
    @Column(length = 12, nullable = false)
    private String registroGeral;

    //  Relacionamentos
    @OneToMany(mappedBy = "usuario")
    List<Turismo> turismos;

    @OneToMany(mappedBy = "usuario")
    List<Review> reviews;
    
    @OneToMany(mappedBy = "usuario")
    List<Voucher> vouchers;
    
    @OneToMany(mappedBy = "usuario")
    List<Curtida> curtidas;

    //  Timestamps
    @Column(nullable = false)
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private LocalDateTime dataEdicao;

}
