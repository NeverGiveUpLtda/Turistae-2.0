package com.api.turistae.dtos;

import java.time.LocalDateTime;
import java.util.List;

import com.api.turistae.models.Turismo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosCategoriaDTO {

    // Atributos
    private long id;
    private String nome;

    // Relacionamentos
    private List<Turismo> turismos;

    // Timestamps
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;

}
