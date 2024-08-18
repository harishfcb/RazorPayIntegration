package com.payment.paymentintegration.controller;

import com.payment.paymentintegration.helper.MailHelper;
import com.payment.paymentintegration.model.CreatedOrder;
import com.payment.paymentintegration.model.PaymentRequest;
import com.payment.paymentintegration.service.PaymentService;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.*;

import java.net.CacheRequest;

@RestController
@RequestMapping("/api/payments")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PaymentController {

    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;

    }


    @PostMapping("/create/order")
    public String createOrder(@RequestBody PaymentRequest paymentRequest){
        try {
            Order order = paymentService.createOrder(paymentRequest);
            return order.toString();
        } catch (RazorpayException e) {
            return "Error creating order: " + e.getMessage();
        }
    }


    @PostMapping("/capture-payment")
    public String capturePayment(@RequestParam String paymentId, @RequestParam String amount) {
        try {
            return paymentService.capturePayment(paymentId, amount).toString();
        } catch (Exception e) {
            return "Error capturing payment: " + e.getMessage();
        }
    }


    @PostMapping("/make-payment")
    public String makePayment(@RequestBody PaymentRequest paymentRequest){

        try {
            Order order = paymentService.makePayment(paymentRequest);
            return order.toString();

        }catch (RazorpayException e){
            return "Error Captured During Exception " +e.getMessage();
        }
    }




}
