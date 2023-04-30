package com.api.turistae.exceptions;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

public class ApiError {

    //  Atributos
    @Getter
    private List<String> errors;
    
    public ApiError(String erro) {
        this.errors = Arrays.asList(erro);
    }
}
