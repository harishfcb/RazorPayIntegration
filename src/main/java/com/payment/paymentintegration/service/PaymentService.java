package com.payment.paymentintegration.service;

import com.payment.paymentintegration.helper.RazorpayMapper;
import com.payment.paymentintegration.model.PaymentRequest;
import com.payment.paymentintegration.model.RazorPayOrder;
import com.payment.paymentintegration.repository.RazorpayOrderRepository;
import com.razorpay.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    private final RazorpayClient razorpayClient;
    private final RazorpayOrderRepository razorpayOrderRepository;
    private final RazorpayMapper razorpayMapper;

    private static final String CREATED="created";

    public PaymentService(@Value("${razorpay.key_id}") String keyId,
                          @Value("${razorpay.key_secret}") String keySecret, RazorpayOrderRepository razorpayOrderRepository, RazorpayMapper razorpayMapper) throws Exception {
        this.razorpayOrderRepository = razorpayOrderRepository;
        this.razorpayMapper = razorpayMapper;
        this.razorpayClient = new RazorpayClient(keyId, keySecret);
    }

    public Order createOrder(PaymentRequest paymentRequest) throws RazorpayException {

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", paymentRequest.getAmount());
        orderRequest.put("currency", paymentRequest.getCurrency());
        orderRequest.put("receipt", "#receipt1");
        log.info("OrderRequest : " + orderRequest);

        return razorpayClient.orders.create(orderRequest);

    }

    public Payment capturePayment(String paymentId, String amount) throws RazorpayException {
        JSONObject captureRequest = new JSONObject();
        captureRequest.put("amount", amount);
        return razorpayClient.payments.capture(paymentId,captureRequest);
    }


    public Order makePayment(PaymentRequest paymentRequest) throws RazorpayException {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", paymentRequest.getAmount());
        orderRequest.put("currency", paymentRequest.getCurrency());
        orderRequest.put("receipt", "#receipt1");
        log.info("OrderRequest : " + orderRequest);

        Order order= razorpayClient.orders.create(orderRequest);



        log.info("Order Status : " + order.get("status"));

        if(order.get("status").equals(CREATED)){
            log.info("Trying to save Order");
            RazorPayOrder razorPayOrder=razorpayMapper.constructRazorPayOrder(order);
            RazorPayOrder saved=razorpayOrderRepository.save(razorPayOrder);

            log.info("Order created : " + razorPayOrder);
            log.info("Saved Order : " +saved);
        }
        return order;

    }
}
