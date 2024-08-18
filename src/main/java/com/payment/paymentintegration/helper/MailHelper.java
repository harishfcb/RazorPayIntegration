package com.payment.paymentintegration.helper;

import com.payment.paymentintegration.model.CreatedOrder;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
public class MailHelper {

    private final TemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    public MailHelper(TemplateEngine templateEngine, JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    public String sendOrderConfirmationEmail(CreatedOrder order) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        Map<String, Object> map = new HashMap<>();
        map.put("customerName", order.getName());
        map.put("orderItems", order.getOrderItems());
        map.put("totalAmount", order.getTotalAmount());
        map.put("orderTrackingUrl", order.getOrderTrackingUrl());

        Context context = new Context();
        context.setVariables(map);

        mimeMessageHelper.setTo(order.getEmailId());
        mimeMessageHelper.setFrom("portfolio@portfolio.com");
        mimeMessageHelper.setSubject("Order Confirmation");

        String html = templateEngine.process("OrderConfirmMail.html", context);
        mimeMessageHelper.setText(html, true);

        javaMailSender.send(mimeMessage);

        return "Mail Sent Successfully";
    }
}
