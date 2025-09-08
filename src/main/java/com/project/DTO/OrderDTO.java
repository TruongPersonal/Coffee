package com.project.DTO;

import java.time.LocalDateTime;

public class OrderDTO {
    private int id;
    private String order_status;
    private LocalDateTime createdAt;
    private int paymentMethod_id;
    private int acount_id;

    public OrderDTO(String order_status, int paymentMethod_id, int acount_id) {
        this.order_status = order_status;
        this.paymentMethod_id = paymentMethod_id;
        this.acount_id = acount_id;
    }

    public OrderDTO(int id, String order_status, LocalDateTime createdAt, int paymentMethod_id, int acount_id) {
        this.id = id;
        this.order_status = order_status;
        this.createdAt = createdAt;
        this.paymentMethod_id = paymentMethod_id;
        this.acount_id = acount_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getPaymentMethod_id() {
        return paymentMethod_id;
    }

    public void setPaymentMethod_id(int paymentMethod_id) {
        this.paymentMethod_id = paymentMethod_id;
    }

    public int getAcount_id() {
        return acount_id;
    }

    public void setAcount_id(int acount_id) {
        this.acount_id = acount_id;
    }

}
