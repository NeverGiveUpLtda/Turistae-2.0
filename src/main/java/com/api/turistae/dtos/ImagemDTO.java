package com.api.turistae.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImagemDTO {

    // Atributos
    private long id;

    @NotBlank(message = "Imagem inválida.")
    private String string64;

    // Relacionamentos
    private long turismoId;

    // Timestamps
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;

}
