package com.api.turistae.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.turistae.models.Turismo;
import com.api.turistae.models.Turista;
import com.api.turistae.models.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    List<Voucher> findAllByTurismo(Turismo turismo);

    List<Voucher> findAllByTurismoAndTuristaIsNull(Turismo turismo);

    List<Voucher> findAllByTurismoAndTuristaIsNotNull(Turismo turismo);

    List<Voucher> findAllByTurista(Turista turista);

}
