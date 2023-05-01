package com.api.turistae.utils;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api.turistae.exceptions.CriptografiaException;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Criptografia {

    // Atributos
    private static final Logger logger = LoggerFactory.getLogger(Criptografia.class);
    
    // Métodos
    
    public static String criptografarSenha(String senha) throws CriptografiaException {

        logger.info("Criptografando senha...");

        try {

            // Crie uma instância da classe MessageDigest com o algoritmo SHA-256
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Converta a senha para um array de bytes e realize a criptografia
            byte[] senhaCriptografada = messageDigest.digest(senha.getBytes());

            // Converta o array de bytes para uma string hexadecimal
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < senhaCriptografada.length; i++) {
                String hex = Integer.toHexString(0xff & senhaCriptografada[i]);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }

            logger.info("Senha criptografada: {}", hexString);

            return hexString.toString();

        } catch (Exception e) {

            // Trate o erro caso ocorra
            logger.error("Erro: Não foi possível criptografar a senha.");
            throw new CriptografiaException("Erro: Não foi possível criptografar a senha.");
        }
    }
}
