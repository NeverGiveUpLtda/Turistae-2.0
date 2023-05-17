package com.api.turistae;

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
import com.api.turistae.models.Usuario;
import com.api.turistae.models.Voucher;
import com.api.turistae.repositorys.CategoriaRepository;
import com.api.turistae.repositorys.CurtidaRepository;
import com.api.turistae.repositorys.ImagemRepository;
import com.api.turistae.repositorys.ReviewRepository;
import com.api.turistae.repositorys.TurismoRepository;
import com.api.turistae.repositorys.UsuarioRepository;
import com.api.turistae.repositorys.VoucherRepository;
import com.api.turistae.utils.CriptografiaUtils;
import com.api.turistae.utils.DataUtils;

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

            geraMock(4, categoriaRepository, curtidaRepository, imagemRepository, reviewRepository, turismoRepository, usuarioRepository, voucherRepository);
        };
    }

    private void geraMock(int i, CategoriaRepository categoriaRepository, CurtidaRepository curtidaRepository, ImagemRepository imagemRepository, ReviewRepository reviewRepository, TurismoRepository turismoRepository, UsuarioRepository usuarioRepository, VoucherRepository voucherRepository) {

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

            Usuario usuario;
            Curtida curtida;
            Imagem imagem;
            Review review;
            Turismo turismo;
            Voucher voucher;

            for(int x = 0; x < i; x++) {

                usuario = new Usuario();
                usuario.setBairro("Bairro " + x);
                usuario.setCadastroPessoaFisica("111.111.111-" + (x + 10));
                usuario.setCidade("Cidade " + x);
                usuario.setDataCriacao(DataUtils.getDataAtualComMascara());
                usuario.setDataEdicao(DataUtils.getDataAtualComMascara());
                usuario.setDataNascimento(DataUtils.getDataAtualComMascara());
                usuario.setEmail("email" + x + "@gmail.com");
                usuario.setEstado("XX");
                usuario.setNome("Nome " + x);
                usuario.setNomeUsuario("usuario" + x);
                usuario.setNumeroCasa(x);
                usuario.setProfissao("Profissão " + x);
                usuario.setRegistroGeral("11.111.111-" + x);
                usuario.setRua("Rua " + x);
                try {
                    usuario.setSenha(CriptografiaUtils.criptografarSenha("Senha@123"));
                } catch(CriptografiaException e) {
                    usuario.setSenha("Senha@123");
                }
                usuario.setTelefone(x);
                Usuario usuarioSalvo = usuarioRepository.save(usuario);
                logger.info(USUARIO_SALVO, usuarioSalvo);

                turismo = new Turismo();
                turismo.setBairro(usuarioSalvo.getBairro());
                turismo.setCadastroNacionalPessoasJuridicas("12.345.678/0001-" + (x + 10));
                turismo.setCategoria(categoriaSalva);
                turismo.setCidade(usuarioSalvo.getCidade());
                turismo.setDataCriacao(usuarioSalvo.getDataCriacao());
                turismo.setDataEdicao(usuarioSalvo.getDataEdicao());
                turismo.setDescricao("Descrição " + x);
                turismo.setEstado(usuarioSalvo.getEstado());
                turismo.setNome("Nome " + x);
                turismo.setNumeroLocal(x);
                turismo.setRua(usuarioSalvo.getRua());
                turismo.setTelefone(x);
                turismo.setUsuario(usuarioSalvo);
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
                curtida.setUsuario(usuarioSalvo);
                Curtida curtidaSalva = curtidaRepository.save(curtida);
                logger.info(CURTIDA_SALVA, curtidaSalva);

                review = new Review();
                review.setDataCriacao(curtida.getDataCriacao());
                review.setDataEdicao(curtida.getDataEdicao());
                review.setNota(10);
                review.setTexto("Texto review " + x);
                review.setTurismo(turismoSalvo);
                review.setUsuario(usuarioSalvo);
                Review reviewSalva = reviewRepository.save(review);
                logger.info(REVIEW_SALVO, reviewSalva);

                voucher = new Voucher();
                voucher.setCodigo("Código: " + x);
                voucher.setDataCriacao(review.getDataCriacao());
                voucher.setDataEdicao(review.getDataEdicao());
                voucher.setTurismo(turismoSalvo);
                voucher.setUsuario(usuarioSalvo);
                Voucher voucherSalvo = voucherRepository.save(voucher);
                logger.info(VOUCHER_SALVO, voucherSalvo);
            }

    }

}
