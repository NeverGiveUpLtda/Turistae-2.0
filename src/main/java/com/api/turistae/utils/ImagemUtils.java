package com.api.turistae.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ImagemUtils {

    // Atributos
    private static final Logger logger = LoggerFactory.getLogger(ImagemUtils.class);

    public static String validarImagem(String string64) {
        logger.info("validando: {}", string64);
        byte[] bytes = null;
        try {
            bytes = Base64.getDecoder().decode(string64);
        } catch (Exception e) {
            logger.error("inválido: formato Base64 inválido.");
            return "formato Base64 inválido.";
        }
        if (bytes.length > 5 * 1024 * 1024) {
            logger.error("inválido: maior que 5mb.");
            return "maior que 5mb.";
        }
        try {
            String extensao = obterExtensao(bytes);
            if (!extensaoPermitida(extensao)) {
                logger.error("inválido: extensão .{} não permitida.", extensao);
                return "extensão " + extensao + " não permitida.";
            }
            if (contemTagsScript(string64)) {
                logger.error("inválido: Imagem contém scripts.");
                return "imagem inválida.";
            }
            return "valida";
        } catch (IOException e) {
            logger.error("inválido: erro ao ler a imagem.");
            return "erro ao ler a imagem.";
        }
    }

    private static String obterExtensao(byte[] bytes) throws IOException {
        String extensao = null;
        try (ImageInputStream imageInputStream = ImageIO.createImageInputStream(new ByteArrayInputStream(bytes))) {
            Iterator<ImageReader> readers = ImageIO.getImageReaders(imageInputStream);
            if (readers.hasNext()) {
                ImageReader reader = readers.next();
                extensao = reader.getFormatName();
            }
        }
        return extensao;
    }

    private static boolean extensaoPermitida(String extensao) {
        return extensao != null && (extensao.equalsIgnoreCase("jpeg") || extensao.equalsIgnoreCase("jpg")
                || extensao.equalsIgnoreCase("png"));
    }

    private static boolean contemTagsScript(String string64) {
        String[] tags = { "script", "iframe", "img", "embed", "object" };
        for (String tag : tags) {
            if (string64.contains("<" + tag) || string64.contains("</" + tag)) {
                return true;
            }
        }
        return false;
    }
}
