package com.pulsecrm.pulsecrm.repository;

import com.pulsecrm.pulsecrm.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByClientId(Long clientId);
}
