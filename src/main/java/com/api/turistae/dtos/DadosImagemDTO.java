package com.api.turistae.dtos;

import java.time.LocalDateTime;

import com.api.turistae.models.Turismo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosImagemDTO {

    // Atributos
    private long id;
    private String url;

    // Relacionamentos
    private Turismo turismo;

    // Timestamps
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;

}
