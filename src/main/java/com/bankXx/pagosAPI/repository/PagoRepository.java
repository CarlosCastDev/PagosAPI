package com.bankXx.pagosAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bankXx.pagosAPI.entity.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}
