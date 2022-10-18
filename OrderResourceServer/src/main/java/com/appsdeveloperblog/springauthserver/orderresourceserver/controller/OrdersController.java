package com.appsdeveloperblog.springauthserver.orderresourceserver.controller;

import com.appsdeveloperblog.springauthserver.orderresourceserver.dto.OrderStatus;
import com.appsdeveloperblog.springauthserver.orderresourceserver.dto.OrdersDTO;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class OrdersController {

    @GetMapping(value = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OrdersDTO> getOrders() {

        OrdersDTO ordersDTO = new OrdersDTO(UUID.randomUUID().toString(), "productId-1",
                "userId-1", 1, OrderStatus.NEW);

        OrdersDTO ordersDTO1 = new OrdersDTO(UUID.randomUUID().toString(), "productId-2",
                "userId-2", 1, OrderStatus.NEW);

        return List.of(ordersDTO, ordersDTO1);

    }
}
