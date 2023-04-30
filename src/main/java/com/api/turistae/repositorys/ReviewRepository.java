package com.api.turistae.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.turistae.models.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    
}
