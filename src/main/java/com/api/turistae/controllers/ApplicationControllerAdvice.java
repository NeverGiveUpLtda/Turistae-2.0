package com.api.turistae.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.turistae.exceptions.ApiError;
import com.api.turistae.exceptions.RegraNegocioException;

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
}
