package com.api.turistae.constraints;

import com.api.turistae.utils.ImagemUtils;
import com.api.turistae.validations.ImagemValidacao;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ImagemConstraint implements ConstraintValidator<ImagemValidacao, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            String validacao = ImagemUtils.validarImagem(value);
            context.disableDefaultConstraintViolation(); // Desabilita a mensagem de erro padrão
            context.buildConstraintViolationWithTemplate("A imagem não é válida: " + validacao).addConstraintViolation(); // Adiciona a mensagem personalizada
            return "valida".equals(validacao);
        } catch (Exception e) {
            return false;
        }
    }

}
