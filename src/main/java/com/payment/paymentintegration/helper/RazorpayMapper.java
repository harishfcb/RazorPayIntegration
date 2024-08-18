package com.payment.paymentintegration.helper;

import com.payment.paymentintegration.model.RazorPayOrder;
import com.razorpay.Order;
import org.json.JSONArray;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Component
public class RazorpayMapper {

    public RazorPayOrder constructRazorPayOrder(Order order) {
        RazorPayOrder razorPayOrder = new RazorPayOrder();

        if (order.get("amount") instanceof Number) {
            razorPayOrder.setAmount(((Number) order.get("amount")).intValue());
        }

        if (order.get("id") instanceof String) {
            razorPayOrder.setOrderId(((String) order.get("id")));
        }


        if (order.get("amount_paid") instanceof Number) {
            razorPayOrder.setAmountPaid(((Number) order.get("amount_paid")).intValue());
        }

        if (order.get("notes") instanceof JSONArray) {
            JSONArray notesArray = (JSONArray) order.get("notes");
            List<String> notesList = new ArrayList<>();
            for (int i = 0; i < notesArray.length(); i++) {
                notesList.add(notesArray.getString(i));
            }
            razorPayOrder.setNotes(notesList);
        }

        if (order.get("created_at") instanceof String) {
            razorPayOrder.setCreatedAt(Instant.parse((String) order.get("created_at")));
        }

        if (order.get("amount_due") instanceof Number) {
            razorPayOrder.setAmountDue(((Number) order.get("amount_due")).intValue());
        }

        if (order.get("currency") instanceof String) {
            razorPayOrder.setCurrency((String) order.get("currency"));
        }

        if (order.get("receipt") instanceof String) {
            razorPayOrder.setReceipt((String) order.get("receipt"));
        }

        if (order.get("entity") instanceof String) {
            razorPayOrder.setEntity((String) order.get("entity"));
        }

        if (order.get("offer_id") instanceof String) {
            razorPayOrder.setOfferId((String) order.get("offer_id"));
        }

        if (order.get("attempts") instanceof Number) {
            razorPayOrder.setAttempts(((Number) order.get("attempts")).intValue());
        }

        if (order.get("status") instanceof String) {
            razorPayOrder.setStatus((String) order.get("status"));
        }

        return razorPayOrder;
    }
}
