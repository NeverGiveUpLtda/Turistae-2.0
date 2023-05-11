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
public class Categoria {

     // Atributos
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;

     @Column(length = 200, nullable = false, unique = true)
     private String nome;

     // Relacionamentos
     @OneToMany(mappedBy = "categoria", fetch = FetchType.EAGER)
     private List<Turismo> turismos;

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
