package com.api.turistae;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class TuristaeApplication {

    // Atributos
    private static final String CATEGORIA_SALVA = "Categoria salva: {}";
    private static final String USUARIO_SALVO = "Usuario salvo: {}";
    private static final String CURTIDA_SALVA = "Curtida salva: {}";
    private static final String IMAGEM_SALVA = "Imagem salva: {}";
    private static final String REVIEW_SALVO = "Review salvo: {}";
    private static final String TURISMO_SALVO = "Turismo salvo: {}";
    private static final String VOUCHER_SALVO = "Voucher salvo: {}";

    // Logger do projeto
    private static final Logger logger = LoggerFactory.getLogger(TuristaeApplication.class);
    private static final Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(TuristaeApplication.class, args);
        logger.info("Boot finalizado. Ouvindo porta 8080");
    }
}
