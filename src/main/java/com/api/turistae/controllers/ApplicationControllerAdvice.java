package com.api.turistae.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.turistae.exceptions.ApiError;
import com.api.turistae.exceptions.RegraNegocioException;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApplicationControllerAdvice {
    @ExceptionHandler(RegraNegocioException.class)
    public ApiError handlerRegraNegocioException(RegraNegocioException ex) {
        return new ApiError(ex.getMessage());
    }
}
