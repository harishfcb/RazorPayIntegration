package com.payment.paymentintegration.service;


import com.payment.paymentintegration.helper.MailHelper;
import com.payment.paymentintegration.model.CreatedOrder;
import com.payment.paymentintegration.model.OrderItem;
import com.payment.paymentintegration.model.ShippingAddress;
import com.payment.paymentintegration.repository.CreatedOrderRepository;
import com.payment.paymentintegration.repository.OrderItemRepository;
import com.payment.paymentintegration.repository.ShippingAddressRepository;
import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.id.uuid.UuidGenerator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PurchaseOrderService {


    private final CreatedOrderRepository createdOrderRepository;

    private final OrderItemRepository orderItemRepository;

    private final MailHelper mailHelper;

    private final ShippingAddressRepository shippingAddressRepository;

    private static final String ORDER_PREFIX = "SHK";

    public PurchaseOrderService(CreatedOrderRepository createdOrderRepository, OrderItemRepository orderItemRepository, MailHelper mailHelper, ShippingAddressRepository shippingAddressRepository) {
        this.createdOrderRepository = createdOrderRepository;
        this.orderItemRepository = orderItemRepository;
        this.mailHelper = mailHelper;
        this.shippingAddressRepository = shippingAddressRepository;
    }

    public CreatedOrder savepurchaseOrder(CreatedOrder createdOrder) throws MessagingException {
        ShippingAddress shippingAddress = createdOrder.getShippingAddress();
        if (shippingAddress != null) {
            shippingAddress = shippingAddressRepository.save(shippingAddress);
            createdOrder.setShippingAddress(shippingAddress);
        }

        String orderId = ORDER_PREFIX + UUID.randomUUID().toString();
        createdOrder.setOrderId(orderId);
        CreatedOrder savedOrder = createdOrderRepository.save(createdOrder);

        List<OrderItem> orderItems = createdOrder.getOrderItems();
        log.info("Created order : " + orderItems.size());

        for (OrderItem item : orderItems) {
            item.setCreatedOrder(savedOrder);
        }

        orderItemRepository.saveAll(orderItems);

        return savedOrder;

    }
}
