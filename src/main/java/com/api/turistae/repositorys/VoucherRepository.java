package com.api.turistae.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.turistae.models.Turismo;
import com.api.turistae.models.Usuario;
import com.api.turistae.models.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    List<Voucher> findAllByTurismo(Turismo turismo);

    List<Voucher> findAllByTurismoAndUsuarioIsNull(Turismo turismo);

    List<Voucher> findAllByTurismoAndUsuarioIsNotNull(Turismo turismo);

    List<Voucher> findAllByUsuario(Usuario usuario);

    @Modifying
    @Query("DELETE FROM Voucher v WHERE v.usuario.id = :usuarioId")
    void deleteByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Modifying
    @Query("DELETE FROM Voucher v WHERE v.turismo.id = :turismoId")
    void deleteByTurismoId(@Param("turismoId") Long turismoId);
}
