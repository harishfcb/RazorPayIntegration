package com.payment.paymentintegration.service;

import com.razorpay.Payment;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RazorPayWebhookService {

    private final RazorpayClient razorpayClient;
    private static final String PAYMENT_AUTHORIZED = "payment.authorized";

    public RazorPayWebhookService(@Value("${razorpay.key_id}") String keyId,
                                  @Value("${razorpay.key_secret}") String keySecret) throws RazorpayException {
        this.razorpayClient = new RazorpayClient(keyId,keySecret);
    }


    public void processWebhookEvent(String payload) throws Exception {
        JSONObject eventJson = new JSONObject(payload);
        String eventType = eventJson.getString("event");
        log.info("Received Webhook Event: " + eventType);
        JSONObject eventData = eventJson.getJSONObject("payload").getJSONObject("payment").getJSONObject("entity");

        log.info("Webhook Event Data: " + eventData.toString());

        if (PAYMENT_AUTHORIZED.equals(eventType)) {
            String paymentId = eventData.getString("id");
            int amount = eventData.getInt("amount");
            boolean captured = eventData.getBoolean("captured");
            log.info("Payment Details - ID: " + paymentId + ", Amount: " + amount + ", Captured: " + captured);

            if (!captured) {
                try {
                    Payment payment = razorpayClient.payments.fetch(paymentId);
                    log.info("Fetched Payment: " + payment.toString());
                    JSONObject captureRequest = new JSONObject();
                    captureRequest.put("amount", amount);
                    Payment capturePayment = razorpayClient.payments.capture(paymentId, captureRequest);
                    log.info("Captured Payment: " + capturePayment.toString());
                } catch (RazorpayException e) {
                    log.error("Error during payment capture", e);
                    throw e;
                }
            } else {
                log.info("Payment already captured.");
            }
        } else {
            log.warn("Unhandled Event Type: " + eventType);
        }
    }



}
