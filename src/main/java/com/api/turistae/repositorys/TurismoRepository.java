package com.api.turistae.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.turistae.models.Turismo;

public interface TurismoRepository extends JpaRepository<Turismo, Long> {
    
}
