package com.api.turistae.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.turistae.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
