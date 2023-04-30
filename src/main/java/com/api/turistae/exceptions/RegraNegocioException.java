package com.api.turistae.exceptions;

public class RegraNegocioException extends RuntimeException {
    public RegraNegocioException(String erro) {
        super(erro);
    }
}
