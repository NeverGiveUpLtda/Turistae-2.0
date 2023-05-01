package com.api.turistae.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DataUtils {

    //  Atributos
    private static final Logger logger = LoggerFactory.getLogger(DataUtils.class);

    // Métodos
    public static LocalDateTime getDataAtualComMascara(String mascara) {
        String data = LocalDateTime.now().format(DateTimeFormatter.ofPattern(mascara));
        logger.info("Retornando data: {}", data);
        
        return LocalDateTime.parse(data, DateTimeFormatter.ofPattern(mascara));
    }

    public static LocalDateTime formataData(LocalDateTime data, String mascara) {
        logger.info("Data antes de formatada: {}", data);
        String dataFormatada = data.format(DateTimeFormatter.ofPattern(mascara));
        logger.info("Data formatada: {}", dataFormatada);

        return LocalDateTime.parse(dataFormatada, DateTimeFormatter.ofPattern(mascara));
    }

}
