package com.api.turistae.dtos;

import jakarta.validation.constraints.Email;
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
public class UsuarioDTO {

    @NotBlank(message = "O campo Email deve ser preencido.")
    @Email(message = "Email inválido.")
    private String email;

    @NotBlank(message = "O campo Nome de Usuário deve ser preencido.")
    @Size(min = 5, max = 25, message = "O campo Nome de Usuário deve ter entre 5 e 25 caracteres.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Nome de usuário inválido. Insira um nome de usuário sem espaços e sem caracteres especiais.")
    private String nome;

    @NotBlank(message = "O campo Senha deve ser preencido.")
    @Size(min = 5, max = 25, message = "O campo Senha deve ter entre 5 e 25 caracteres.")
    @Pattern(regexp = "^(?=.*[!@#$%^&+=])(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{5,25}$", message = "Senha inválida. Insira uma senha com 1 letra maiúscula, 1 letra minúscula, 1 número e um caractere especial.")
    private String senha;

    private String perfil;
}
