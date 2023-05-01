package com.api.turistae.models;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Turismo {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 200, nullable = false)
    private String nome;

    @Column(nullable = false)
    private long telefone;

    @Column(nullable = false)
    private int numeroLocal;

    @Column(length = 200, nullable = false)
    private String rua;

    @Column(length = 200, nullable = false)
    private String bairro;

    @Column(length = 200, nullable = false)
    private String cidade;

    @Column(length = 2, nullable = false)
    private String estado;

    @Column(length = 18, nullable = false, unique = true)
    private String cadastroNacionalPessoasJuridicas;

    @Column(length = 400, nullable = false)
    private String descricao;

    // Relacionamentos
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @OneToMany(mappedBy = "turismo")
    @Column(nullable = false)
    private List<Imagem> imagens;

    @OneToMany(mappedBy = "turismo")
    private List<Review> reviews;

    @OneToMany(mappedBy = "turismo")
    private List<Voucher> vouchers;

    @OneToMany(mappedBy = "turismo")
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
