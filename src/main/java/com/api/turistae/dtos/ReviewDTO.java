package com.api.turistae.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewDTO {

     // Atributos
     private long id;

     @NotBlank(message = "O campo Texto deve ser preencido.")
     @Size(min = 3, max = 200, message = "O campo Texto deve ter entre 3 e 200 caracteres.")
     @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$", message = "Campo Texto com caracteres inválidos. Insira apenas letras.")
     private String texto;

     @NotNull(message = "O campo Nota deve ser preencido.")
     @DecimalMin(value = "0", message = "O campo Nota deve ser maior ou igual a 0.")
     @DecimalMax(value = "10", message = "O campo Nota deve ser menor ou igual a 10.")
     private int nota;

     // Relacionamentos
     private long turismoId;
     private long turistaId;

     // Timestamps
     private LocalDateTime dataCriacao;
     private LocalDateTime dataEdicao;

}
