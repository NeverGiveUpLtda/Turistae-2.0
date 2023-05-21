package com.api.turistae.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosReviewDTO {

    // Atributos
    private long id;
    private String texto;
    private int nota;

    // Relacionamentos
    private TurismoDTO turismo;
    private TuristaDTO turista;

    // Timestamps
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;

}
