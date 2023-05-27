package com.api.turistae.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.api.turistae.constraints.ImagemConstraint;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = ImagemConstraint.class)
public @interface ImagemValidacao {

    String message() default "Imagem inválida.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
