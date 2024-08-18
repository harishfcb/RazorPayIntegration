package com.payment.paymentintegration.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "razorpay_order")
public class RazorPayOrder {
    @Id
    private String orderId;
    private int amount;
    private int amountPaid;

    @ElementCollection
    @CollectionTable(name = "razorpay_order_notes", joinColumns = @JoinColumn(name = "razorpay_order_id"))
    @Column(name = "note")
    private List<String> notes;
    private Instant createdAt;
    private int amountDue;
    private String currency;
    private String receipt;
    private String entity;
    private String offerId;
    private int attempts;
    private String status;
}
