package com.api.turistae.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.turistae.models.Categoria;
import com.api.turistae.models.Turismo;
import com.api.turistae.models.Usuario;

public interface TurismoRepository extends JpaRepository<Turismo, Long> {

    List<Turismo> findAllByOrderByCurtidasDesc();

    List<Turismo> findAllByCategoria(Categoria categoria);

    List<Turismo> findAllByUsuario(Usuario usuario);

    @Query("SELECT t FROM Turismo t LEFT JOIN t.reviews r GROUP BY t ORDER BY AVG(r.nota) DESC")
    List<Turismo> findAllOrderByMediaNotaDesc();

    @Modifying
    @Query("DELETE FROM Turismo t WHERE t.usuario.id = :usuarioId")
    void deleteByUsuarioId(@Param("usuarioId") Long usuarioId);

}
