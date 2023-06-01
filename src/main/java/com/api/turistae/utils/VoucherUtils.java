package com.api.turistae.utils;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VoucherUtils {

    // Atributos
    private static Random random = new Random(System.currentTimeMillis());
    private static final Logger logger = LoggerFactory.getLogger(VoucherUtils.class);

    // Métodos
    public static String gerarVoucher(long id) {
        logger.info("Gerando voucher para turismo id: {}", id);

        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";

        StringBuilder code = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            code.append(letters.charAt(random.nextInt(letters.length())));
        }
        code.append("-");

        for (int i = 0; i < 3; i++) {
            code.append(letters.charAt(random.nextInt(letters.length())));
        }
        code.append("-");

        for (int i = 0; i < 4; i++) {
            code.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        code.append(id);

        logger.info("Voucher gerado com sucesso!");

        return code.toString();
    }
}
