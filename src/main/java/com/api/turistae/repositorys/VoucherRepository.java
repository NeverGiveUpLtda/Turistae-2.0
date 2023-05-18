package com.api.turistae.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.turistae.models.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {

    //List<Voucher> findAllByTurismoId(Long turismoId)

}
