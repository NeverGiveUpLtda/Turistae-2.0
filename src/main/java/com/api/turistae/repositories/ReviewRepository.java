package com.api.turistae.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.turistae.models.Review;
import com.api.turistae.models.Turismo;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByTurismo(Turismo turismo);

}
