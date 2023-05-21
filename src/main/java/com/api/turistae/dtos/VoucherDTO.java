package com.api.turistae.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VoucherDTO {

    // Atributos
    private long id;
    private String codigo;

    @NotNull(message = "O campo Valor deve ser preencido.")
    @DecimalMin(value = "0", message = "O campo Valor deve ser maior ou igual a 0.")
    @DecimalMax(value = "100", message = "O campo Valor deve ser menor ou igual a 100.")
    private int valor;

    // Relacionamentos
    private long turismoId;
    private long turistaId;

    // Timestamps
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;

}
