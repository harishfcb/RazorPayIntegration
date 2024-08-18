package com.payment.paymentintegration.repository;

import com.payment.paymentintegration.model.CreatedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreatedOrderRepository extends JpaRepository<CreatedOrder,Long> {
}
