package com.api.turistae.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.turistae.models.Turismo;
import com.api.turistae.models.Usuario;
import com.api.turistae.models.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    List<Voucher> findAllByTurismo(Turismo turismo);

    List<Voucher> findAllByTurismoAndUsuarioIsNull(Turismo turismo);

    List<Voucher> findAllByTurismoAndUsuarioIsNotNull(Turismo turismo);

    List<Voucher> findAllByUsuario(Usuario usuario);

}
