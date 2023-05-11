package com.api.turistae.dtos;

import java.time.LocalDateTime;
import java.util.List;

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

    // Relacionamento
    private List<TurismoDTO> turismos;

    // Timestamps
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;

}
