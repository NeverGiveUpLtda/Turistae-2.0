package com.api.turistae;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.api.turistae.repositorys.CurtidaRepository;
import com.api.turistae.repositorys.ImagemRepository;
import com.api.turistae.repositorys.ReviewRepository;
import com.api.turistae.repositorys.TurismoRepository;
import com.api.turistae.repositorys.UsuarioRepository;
import com.api.turistae.repositorys.VoucherRepository;

@SpringBootApplication
public class TuristaeApplication {

	@Bean
    public CommandLineRunner init(
            @Autowired CurtidaRepository curtidaRepository,
            @Autowired ImagemRepository imagemRepository,
			@Autowired ReviewRepository reviewRepository,
			@Autowired TurismoRepository turismoRepository,
			@Autowired UsuarioRepository usuarioRepository,
			@Autowired VoucherRepository voucherRepository) {
        return args -> {  };
    }

	//  Logger do projeto
    private static final Logger logger = LoggerFactory.getLogger(TuristaeApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TuristaeApplication.class, args);
		logger.info("Projeto iniciado");
	}

}
