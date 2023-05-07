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

    // Logger do projeto
    private static final Logger logger = LoggerFactory.getLogger(TuristaeApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(TuristaeApplication.class, args);
        logger.info("Boot finalizado. Ouvindo porta 8080");
    }

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
            categoria.setNome("Aventura");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            Categoria categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);

            categoria = new Categoria();
            categoria.setNome("Cultural");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);

            categoria = new Categoria();
            categoria.setNome("Histórico");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);

            categoria = new Categoria();
            categoria.setNome("Gastronômico");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);

            categoria = new Categoria();
            categoria.setNome("Natureza");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);

            categoria = new Categoria();
            categoria.setNome("Rural");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Praia");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Montanha");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Ecoturismo");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Observação da Vida Selvagem");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Safári");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Cruzeiro");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Mergulho");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Esportes Aquáticos");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Esqui");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Golfe");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Religioso");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Peregrinação");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Bem-Estar e Spa");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Compras");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Negócios");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Eventos e Conferências");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Festivais e Celebrações");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Arquitetura");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
            
            categoria = new Categoria();
            categoria.setNome("Jardins e Parques");
            categoria.setDataCriacao(DataUtils.getDataAtualComMascara());
            categoria.setDataEdicao(DataUtils.getDataAtualComMascara());
            categoriaSalva = categoriaRepository.save(categoria);
            logger.info(CATEGORIA_SALVA, categoriaSalva);
        };
    }

}
