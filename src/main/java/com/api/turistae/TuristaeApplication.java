package com.api.turistae;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.api.turistae.models.Categoria;
import com.api.turistae.models.Curtida;
import com.api.turistae.models.Imagem;
import com.api.turistae.models.Review;
import com.api.turistae.models.Turismo;
import com.api.turistae.models.Turista;
import com.api.turistae.models.Voucher;
import com.api.turistae.repositories.CategoriaRepository;
import com.api.turistae.repositories.CurtidaRepository;
import com.api.turistae.repositories.ImagemRepository;
import com.api.turistae.repositories.ReviewRepository;
import com.api.turistae.repositories.TurismoRepository;
import com.api.turistae.repositories.TuristaRepository;
import com.api.turistae.repositories.VoucherRepository;
import com.api.turistae.utils.DataUtils;
import com.api.turistae.utils.VoucherUtils;

@SpringBootApplication
public class TuristaeApplication {

    // Atributos
    private static final String CATEGORIA_SALVA = "Categoria salva: {}";
    private static final String TURISTA_SALVO = "Turista salvo: {}";
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

    // Inicializador
    @Bean
    CommandLineRunner init(
            @Autowired CategoriaRepository categoriaRepository,
            @Autowired CurtidaRepository curtidaRepository,
            @Autowired ImagemRepository imagemRepository,
            @Autowired ReviewRepository reviewRepository,
            @Autowired TurismoRepository turismoRepository,
            @Autowired TuristaRepository turistaRepository,
            @Autowired VoucherRepository voucherRepository) {
        return args -> {

            logger.info("Rodando CommandLineRunner");

            geraMock(4, categoriaRepository, curtidaRepository, imagemRepository, reviewRepository, turismoRepository, turistaRepository, voucherRepository);
        };
    }

    private void geraMock(int i, CategoriaRepository categoriaRepository, CurtidaRepository curtidaRepository, ImagemRepository imagemRepository, ReviewRepository reviewRepository, TurismoRepository turismoRepository, TuristaRepository turistaRepository, VoucherRepository voucherRepository) {

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

            Turista turista;
            Curtida curtida;
            Imagem imagem;
            Review review;
            Turismo turismo;
            Voucher voucher;

            for(int x = 0; x < i; x++) {

                turista = new Turista();
                turista.setBairro("Bairro " + x);
                turista.setCadastroPessoaFisica("111.111.111-" + (x + 10));
                turista.setCidade("Cidade " + x);
                turista.setDataCriacao(DataUtils.getDataAtualComMascara());
                turista.setDataEdicao(DataUtils.getDataAtualComMascara());
                turista.setDataNascimento(DataUtils.getDataAtualComMascara());
                turista.setEstado("XX");
                turista.setNome("Nome " + x);
                turista.setNumeroCasa(x);
                turista.setProfissao("Profissão " + x);
                turista.setRegistroGeral("11.111.111-" + x);
                turista.setRua("Rua " + x);
                turista.setTelefone(x);
                Turista turistaSalvo = turistaRepository.save(turista);
                logger.info(TURISTA_SALVO, turistaSalvo);

                turismo = new Turismo();
                turismo.setBairro(turistaSalvo.getBairro());
                turismo.setCadastroNacionalPessoasJuridicas("12.345.678/0001-" + (x + 10));
                turismo.setCategoria(categoriaSalva);
                turismo.setCidade(turistaSalvo.getCidade());
                turismo.setDataCriacao(turistaSalvo.getDataCriacao());
                turismo.setDataEdicao(turistaSalvo.getDataEdicao());
                turismo.setDescricao("Descrição " + x);
                turismo.setEstado(turistaSalvo.getEstado());
                turismo.setNome("Nome " + x);
                turismo.setNumeroLocal(x);
                turismo.setRua(turistaSalvo.getRua());
                turismo.setTelefone(x);
                turismo.setTurista(turistaSalvo);
                Turismo turismoSalvo = turismoRepository.save(turismo);
                logger.info(TURISMO_SALVO, turismoSalvo);

                imagem = new Imagem();
                imagem.setDataCriacao(turismoSalvo.getDataCriacao());
                imagem.setDataEdicao(turismoSalvo.getDataEdicao());
                imagem.setString64("String 64 " + x);
                imagem.setTurismo(turismoSalvo);
                Imagem imagemSalva = imagemRepository.save(imagem);
                logger.info(IMAGEM_SALVA, imagemSalva);

                curtida = new Curtida();
                curtida.setDataCriacao(imagem.getDataCriacao());
                curtida.setDataEdicao(imagem.getDataEdicao());
                curtida.setTurismo(turismoSalvo);
                curtida.setTurista(turistaSalvo);
                Curtida curtidaSalva = curtidaRepository.save(curtida);
                logger.info(CURTIDA_SALVA, curtidaSalva);

                review = new Review();
                review.setDataCriacao(curtida.getDataCriacao());
                review.setDataEdicao(curtida.getDataEdicao());
                review.setNota(10);
                review.setTexto("Texto review " + x);
                review.setTurismo(turismoSalvo);
                review.setTurista(turistaSalvo);
                Review reviewSalva = reviewRepository.save(review);
                logger.info(REVIEW_SALVO, reviewSalva);

                voucher = new Voucher();
                voucher.setCodigo(VoucherUtils.gerarVoucher(x));
                voucher.setValor(random.nextInt(101));
                voucher.setDataCriacao(review.getDataCriacao());
                voucher.setDataEdicao(review.getDataEdicao());
                voucher.setTurismo(turismoSalvo);
                voucher.setTurista(turistaSalvo);
                Voucher voucherSalvo = voucherRepository.save(voucher);
                logger.info(VOUCHER_SALVO, voucherSalvo);
            }

    }

}
