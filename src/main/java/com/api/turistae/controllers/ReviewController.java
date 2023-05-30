package com.api.turistae.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.turistae.dtos.DadosReviewDTO;
import com.api.turistae.dtos.ReviewDTO;
import com.api.turistae.exceptions.RegraNegocioException;
import com.api.turistae.services.ReviewService;
import com.api.turistae.utils.DataUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

    // Atributos
    private ReviewService reviewService;
    private final Logger logger;

    // Construtor
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
        this.logger = LoggerFactory.getLogger(this.getClass());
        logger.info("Review Controller iniciado.");
    }

    // HttpGet
    @GetMapping
    public List<DadosReviewDTO> getReviews() {
        logger.info("Get todos reviews.");
        return reviewService.getAll();
    }

    @GetMapping("/turismo/{id}")
    public List<DadosReviewDTO> getReviewsPorTurismo(@PathVariable Long id) {
        logger.info("Get todas Reviews por turismo id: {}", id);
        return reviewService.getByTurismo(id);
    }

    @GetMapping("{id}")
    public DadosReviewDTO getReviewPorId(@PathVariable Long id) {
        logger.info("Get review id: {}", id);
        return reviewService.getById(id);
    }

    // HttpPost
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long postReview(@Valid @RequestBody ReviewDTO reviewDTO) {


        //TODO
        reviewDTO.setDataCriacao(DataUtils.getDataAtualComMascara());
        reviewDTO.setDataEdicao(DataUtils.getDataAtualComMascara());

        logger.info("Post review: {}", reviewDTO);

        // Se review já exisir na tabela, retornar erro
        // Retorno do cadastro
        Long id = 0l;
        try {
            id = reviewService.post(reviewDTO);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException("Erro ao cadastrar review.");
        }
        return id;
    }

    // HttpPut
    @PutMapping("{id}")
    public void putReview(@PathVariable Long id, @Valid @RequestBody ReviewDTO reviewDTO) {

        //TODO
        reviewDTO.setDataEdicao(DataUtils.getDataAtualComMascara());

        logger.info("Put review id {}: {}", id, reviewDTO);

        // Se review já exisir na tabela, retornar erro
        // Retorno do cadastro
        try {
            reviewService.put(id, reviewDTO);
        } catch (DataIntegrityViolationException e) {
            throw new RegraNegocioException("Erro ao atualizar review.");
        }
    }

    // HttpDelete
    @DeleteMapping("{id}")
    public void deleteReview(@PathVariable Long id) {

        logger.info("Delete review id {}", id);

        reviewService.delete(id);
    }

}
