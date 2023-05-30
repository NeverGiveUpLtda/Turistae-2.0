package com.api.turistae.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.turistae.models.Categoria;
import com.api.turistae.models.Turismo;

public interface TurismoRepository extends JpaRepository<Turismo, Long> {

    List<Turismo> findAllByOrderByCurtidasDesc();

    List<Turismo> findAllByCategoria(Categoria categoria);

    @Query("SELECT t FROM Turismo t LEFT JOIN t.reviews r GROUP BY t ORDER BY AVG(r.nota) DESC")
    List<Turismo> findAllOrderByMediaNotaDesc();

}
