package com.api.turistae.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.turistae.exceptions.ApiError;
import com.api.turistae.exceptions.RegraNegocioException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationControllerAdvice {

    // Atributos
    private static final Logger logger = LoggerFactory.getLogger(ApplicationControllerAdvice.class);

    // Métodos
    @ExceptionHandler(RegraNegocioException.class)
    public ApiError handlerRegraNegocioException(RegraNegocioException ex) {

        logger.error("Erro de regra de negócio: {}", ex.getMessage());
        return new ApiError(ex.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ApiError handlerConstraintViolationException(
            ConstraintViolationException ex) {

        logger.error("Erro de regra de validação de campo: {}", ex.getMessage());

        List<String> erros = ex.getConstraintViolations()
                .stream()
                .map(erro -> erro.getMessage())
                .collect(Collectors.toList());
        return new ApiError(erros);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiError handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        logger.error("Erro de validaão de método : {}", ex.getMessage());

        List<String> erros = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        return new ApiError(erros);
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ApiError handleInvalidFormatException(InvalidFormatException ex) {

        logger.error("Erro de validação de formato : {}", ex.getMessage());

        String field = ex.getPath().get(0).getFieldName();
        String message = String.format("Campo %s com formato inválido.", field);
        return new ApiError(message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ApiError handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        logger.error("Erro de body na requisição: {}", ex.getMessage());

        return new ApiError("Requisição com erro nos parâmetros.");
    }

}
