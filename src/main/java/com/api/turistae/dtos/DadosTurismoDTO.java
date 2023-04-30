package com.api.turistae.dtos;

import java.time.LocalDateTime;

import com.api.turistae.models.Usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosTurismoDTO {

    // Atributos
    private long id;
    private String nome;
    private long telefone;
    private int numeroLocal;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String cadastroNacionalPessoasJuridicas;
    private String descricao;
    private String categoria;

    // Relacionamentos
    private Usuario usuario;

    // Timestamps
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;

}
