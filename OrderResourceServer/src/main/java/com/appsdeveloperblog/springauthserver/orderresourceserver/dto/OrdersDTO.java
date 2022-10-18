package com.appsdeveloperblog.springauthserver.orderresourceserver.dto;

public class OrdersDTO {

    private String orderId;



    private String userId;
    private String productId;
    private int quantity;
    private OrderStatus orderStatus;

    public OrdersDTO(String orderId, String productId, String userId, int quantity, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getUserId() {
        return userId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
}
