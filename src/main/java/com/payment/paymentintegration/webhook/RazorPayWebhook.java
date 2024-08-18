package com.payment.paymentintegration.webhook;

import com.payment.paymentintegration.service.PaymentService;
import com.payment.paymentintegration.service.RazorPayWebhookService;
import com.razorpay.Utils;
import com.razorpay.Webhook;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RazorPayWebhook {


    private final RazorPayWebhookService razorPayWebhookService;

    public RazorPayWebhook(RazorPayWebhookService razorPayWebhookService) {
        this.razorPayWebhookService = razorPayWebhookService;
    }


    @PostMapping("/razorpay/webhook")
    public void handleWebhook(@RequestBody String payload) {
        try {
            log.info("Received webhook payload: " + payload);
            razorPayWebhookService.processWebhookEvent(payload);
        } catch (Exception e) {
            log.error("Error processing webhook event", e);
        }
    }
}
