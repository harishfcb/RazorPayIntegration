package com.payment.paymentintegration.controller;

import com.payment.paymentintegration.helper.MailHelper;
import com.payment.paymentintegration.model.CreatedOrder;
import com.payment.paymentintegration.service.PurchaseOrderService;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/purchase")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PurchaseOrderController {


    private final PurchaseOrderService purchaseOrderService;
    private final MailHelper mailHelper;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService, MailHelper mailHelper) {
        this.purchaseOrderService = purchaseOrderService;
        this.mailHelper = mailHelper;
    }


    @PostMapping("/order/create")
    public ResponseEntity<CreatedOrder> savePurchaseOrder(@RequestBody CreatedOrder createdOrder) throws MessagingException {
        try {
            CreatedOrder savedOrder = purchaseOrderService.savepurchaseOrder(createdOrder);
            return ResponseEntity.ok(savedOrder);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw e;
        }

    }

    @PostMapping("/send-confirmation-mail")
    public String sendmail(@RequestBody CreatedOrder createdOrder) throws MessagingException {
        return  mailHelper.sendOrderConfirmationEmail(createdOrder);
    }




}
