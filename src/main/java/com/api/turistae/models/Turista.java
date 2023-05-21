package com.api.turistae.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Turista {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //TODO
    // @Column(length = 25, nullable = false, unique = true)
    // private String nomeUsuario;

    //TODO
    // @Column(nullable = false)
    // private String senha;

    @Column(length = 200, nullable = false)
    private String nome;

    //TODO
    // @Column(length = 200, nullable = false, unique = true)
    // private String email;

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

    @Column(length = 2, nullable = false)
    private String estado;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime dataNascimento;

    @Column(length = 200, nullable = false)
    private String profissao;

    @Column(length = 14, nullable = false, unique = true)
    private String cadastroPessoaFisica;

    @Column(length = 12, nullable = false, unique = true)
    private String registroGeral;

    // Relacionamentos
    @OneToMany(mappedBy = "turista", fetch = FetchType.EAGER)
    private List<Turismo> turismos;

    @OneToMany(mappedBy = "turista", fetch = FetchType.EAGER)
    private List<Review> reviews;

    @OneToMany(mappedBy = "turista", fetch = FetchType.EAGER)
    private List<Voucher> vouchers;

    @OneToMany(mappedBy = "turista", fetch = FetchType.EAGER)
    private List<Curtida> curtidas;

    // Timestamps
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss")
    private LocalDateTime dataCriacao;

    @CreatedDate
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-mm-ss")
    private LocalDateTime dataEdicao;

}
