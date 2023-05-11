package com.api.turistae.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Voucher {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 200, nullable = false)
    private String codigo;

    // Relacionamentos
    @ManyToOne
    @JoinColumn(name = "turismo_id", nullable = false)
    private Turismo turismo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

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
