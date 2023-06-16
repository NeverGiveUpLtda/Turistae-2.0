package com.api.turistae.dtos;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Email;
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
public class PutUsuarioDTO {
    // Atributos
    private long id;

    @NotBlank(message = "O campo Nome de Usuário deve ser preencido.")
    @Size(min = 5, max = 25, message = "O campo Nome de Usuário deve ter entre 5 e 25 caracteres.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Nome de usuário inválido. Insira um nome de usuário sem espaços e sem caracteres especiais.")
    private String nomeUsuario;

    @NotBlank(message = "O campo Nome deve ser preencido.")
    @Size(min = 3, max = 25, message = "O campo Nome deve ter entre 3 e 25 caracteres.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$", message = "Campo Nome com caracteres inválidos. Insira apenas letras.")
    private String nome;

    @NotBlank(message = "O campo Email deve ser preencido.")
    @Email(message = "Email inválido.")
    private String email;

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

    // Timestamps
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;

}
