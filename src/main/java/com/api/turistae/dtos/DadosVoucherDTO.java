package com.api.turistae.dtos;

import java.time.LocalDateTime;

import com.api.turistae.models.Turismo;
import com.api.turistae.models.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosVoucherDTO {

    //  Atributos
    private long id;
    private String codigo;

    //  Relacionamentos
    private Turismo turismo;
    private Usuario usuario;

    //  Timestamps
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;
    
}
