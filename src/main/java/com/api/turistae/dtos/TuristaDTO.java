package com.api.turistae.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
public class TuristaDTO {

    // Atributos
    private long id;

    @NotBlank(message = "O campo Nome deve ser preencido.")
    @Size(min = 3, max = 25, message = "O campo Nome deve ter entre 3 e 25 caracteres.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$", message = "Campo Nome com caracteres inválidos. Insira apenas letras.")
    private String nome;

    @NotNull(message = "O campo Telefone deve ser preencido.")
    private long telefone;

    @NotNull(message = "O campo Número da Casa deve ser preencido.")
    private int numeroCasa;

    @NotBlank(message = "O campo Rua deve ser preencido.")
    @Size(min = 3, max = 200, message = "O campo Rua deve ter entre 3 e 200 caracteres.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$", message = "Campo Rua com caracteres inválidos. Insira apenas letras.")
    private String rua;

    @NotBlank(message = "O campo Bairro deve ser preencido.")
    @Size(min = 3, max = 200, message = "O campo Bairro deve ter entre 3 e 200 caracteres.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$", message = "Campo Bairro com caracteres inválidos. Insira apenas letras.")
    private String bairro;

    @NotBlank(message = "O campo Cidade deve ser preencido.")
    @Size(min = 3, max = 200, message = "O campo Cidade deve ter entre 3 e 200 caracteres.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$", message = "Campo Cidade com caracteres inválidos. Insira apenas letras.")
    private String cidade;

    @NotBlank(message = "O campo Estado deve ser preencido.")
    @Size(min = 2, max = 2, message = "O campo Estado deve ter 2 caracteres. Ex. SP")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$", message = "Campo Estado com caracteres inválidos. Insira apenas letras.")
    private String estado;

    @NotNull(message = "O campo Data de Nasciento deve ser preencido.")
    @Past(message = "Data inserida inválida, insira uma data que já passou.")
    private LocalDateTime dataNascimento;

    @NotBlank(message = "O campo Profissão deve ser preencido.")
    @Size(min = 3, max = 200, message = "O campo Profissão deve ter entre 3 e 200 caracteres.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$", message = "Campo Profissão com caracteres inválidos. Insira apenas letras.")
    private String profissao;

    @NotBlank(message = "O campo CPF deve ser preencido.")
    @Size(min = 14, max = 14, message = "CPF inválido. Insira um CPF válido com 14 caracteres.")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF inválido. Ex: xxx.xxx.xxx-xx.")
    private String cadastroPessoaFisica;

    @NotBlank(message = "O campo RG deve ser preencido.")
    @Size(min = 12, max = 12, message = "RG inválido. Insira um RG válido com 12 caracteres.")
    @Pattern(regexp = "\\d{2}\\.\\d{3}\\.\\d{3}-\\d{1}", message = "RG inválido. Ex: xx.xxx.xxx-x.")
    private String registroGeral;

    // Timestamps
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;

}
