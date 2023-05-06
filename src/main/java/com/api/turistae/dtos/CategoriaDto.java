package com.api.turistae.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
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
public class CategoriaDTO {

    // Atributos
    private long id;
    
    @NotBlank(message = "O campo Nome deve ser preencido.")
    @Size(min = 3, max = 25, message = "O campo Nome deve ter entre 3 e 200 caracteres.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$", message = "Campo Nome com caracteres inválidos. Insira apenas letras.")
    private String nome;

    // Timestamps
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;

}
