package com.payment.paymentintegration.repository;

import com.payment.paymentintegration.model.RazorPayOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RazorpayOrderRepository extends JpaRepository<RazorPayOrder,Long> {
}
