package com.efashion.payment_service.repository;

import com.efashion.payment_service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepositoty extends JpaRepository<Payment, Integer> {

}
