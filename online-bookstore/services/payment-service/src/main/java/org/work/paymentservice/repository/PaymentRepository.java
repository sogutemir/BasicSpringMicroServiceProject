package org.work.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.work.paymentservice.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

