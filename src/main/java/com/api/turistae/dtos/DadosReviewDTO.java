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
    private byte nota;

    // Relacionamentos
    private TurismoDTO turismo;
    private UsuarioDTO usuario;

    // Timestamps
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;

}
