package com.api.turistae;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.api.turistae.models.Categoria;
import com.api.turistae.repositorys.CategoriaRepository;
import com.api.turistae.repositorys.CurtidaRepository;
import com.api.turistae.repositorys.ImagemRepository;
import com.api.turistae.repositorys.ReviewRepository;
import com.api.turistae.repositorys.TurismoRepository;
import com.api.turistae.repositorys.UsuarioRepository;
import com.api.turistae.repositorys.VoucherRepository;
import com.api.turistae.utils.DataUtils;

@SpringBootApplication
public class TuristaeApplication {

    // Atributos
    private static final String CATEGORIA_SALVA = "Categoria salva: {}";
    private static final String MASCARA_DATA = "yyyy-MM-dd-HH-mm-ss";

    // Inicializador
    @Bean
    CommandLineRunner init(
            @Autowired CategoriaRepository categoriaRepository,
            @Autowired CurtidaRepository curtidaRepository,
            @Autowired ImagemRepository imagemRepository,
            @Autowired ReviewRepository reviewRepository,
            @Autowired TurismoRepository turismoRepository,
            @Autowired UsuarioRepository usuarioRepository,
            @Autowired VoucherRepository voucherRepository) {
        return args -> {
            logger.info("Rodando CommandLineRunner");
            Categoria categoria = new Categoria();
            categoria.setNome("Restaurante");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara(MASCARA_DATA));
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara(MASCARA_DATA));
            Categoria categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);

            categoria = new Categoria();
            categoria.setNome("Bar");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara(MASCARA_DATA));
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara(MASCARA_DATA));
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);

            categoria = new Categoria();
            categoria.setNome("Mercado");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara(MASCARA_DATA));
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara(MASCARA_DATA));
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);

            categoria = new Categoria();
            categoria.setNome("Shopping");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara(MASCARA_DATA));
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara(MASCARA_DATA));
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);

            categoria = new Categoria();
            categoria.setNome("Parque");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara(MASCARA_DATA));
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara(MASCARA_DATA));
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);

        };
    }

    // Logger do projeto
    private static final Logger logger = LoggerFactory.getLogger(TuristaeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TuristaeApplication.class, args);
        logger.info("Boot finalizado. Ouvindo porta 8080");
    }

}
