package com.payment.paymentintegration.repository;

import com.payment.paymentintegration.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
}
