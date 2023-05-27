package com.api.turistae.dtos;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
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
public class TurismoDTO {

    // Atributos
    private long id;

    @NotBlank(message = "O campo Nome deve ser preencido.")
    @Size(min = 3, max = 200, message = "O campo Nome deve ter entre 3 e 200 caracteres.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$", message = "Campo Nome com caracteres inválidos. Insira apenas letras.")
    private String nome;

    @NotNull(message = "O campo Telefone deve ser preencido.")
    private long telefone;

    @NotNull(message = "O campo Número da Casa deve ser preencido.")
    private int numeroLocal;

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

    @NotBlank(message = "O campo CNPJ deve ser preencido.")
    @Size(min = 18, max = 18, message = "CNPJ inválido. Insira um CNPJ válido com 18 caracteres.")
    @Pattern(regexp = "^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$", message = "CNPJ inválido. Ex: xx.xxx.xxx/xxxx-xx.")
    private String cadastroNacionalPessoasJuridica;

    @NotBlank(message = "O campo Nome deve ser preencido.")
    @Size(min = 3, max = 400, message = "O campo Descrição deve ter entre 3 e 400 caracteres.")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ ]+$", message = "Campo Descrição com caracteres inválidos. Insira apenas letras.")
    private String descricao;

    @NotNull(message = "Campo imagens não deve ser nulo.")
    @Valid
    private List<ImagemDTO> imagens;

    // Relacionamentos
    private Long turistaId;
    private Long categoriaId;

    // Timestamps
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEdicao;

}
