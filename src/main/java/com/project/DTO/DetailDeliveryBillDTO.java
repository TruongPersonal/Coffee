package com.project.DTO;

import java.time.LocalDateTime;

public class DetailDeliveryBillDTO {
    private int id;
    private int deliveryBillId;
    private int ingradientId;
    private int quantity;
    private String nameIngradient;
    private String unit;
    private LocalDateTime createdAt;

    public DetailDeliveryBillDTO(
            int id,
            int deliveryBillId,
            int ingradientId,
            int quantity,
            String nameIngradient,
            String unit,
            LocalDateTime createdAt) {
        this.id = id;
        this.deliveryBillId = deliveryBillId;
        this.ingradientId = ingradientId;
        this.quantity = quantity;
        this.nameIngradient = nameIngradient;
        this.unit = unit;
        this.createdAt = createdAt;
    }

    public DetailDeliveryBillDTO(
            int deliveryBillId,
            int ingradientId,
            int quantity) {
        this.deliveryBillId = deliveryBillId;
        this.ingradientId = ingradientId;
        this.quantity = quantity;
    }

    public DetailDeliveryBillDTO(
            int id,
            String nameIngradient,
            int ingradientId,
            int quantity) {
        this.id = id;
        this.nameIngradient = nameIngradient;
        this.ingradientId = ingradientId;
        this.quantity = quantity;
    }

    public DetailDeliveryBillDTO(
            int ingradientId,
            int quantity) {
        this.ingradientId = ingradientId;
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getDeliveryBillId() {
        return deliveryBillId;
    }

    public int getId() {
        return id;
    }

    public int getIngradientId() {
        return ingradientId;
    }

    public String getNameIngradient() {
        return nameIngradient;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setDeliveryBillId(int deliveryBillId) {
        this.deliveryBillId = deliveryBillId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIngradientId(int ingradientId) {
        this.ingradientId = ingradientId;
    }

    public void setNameIngradient(String nameIngradient) {
        this.nameIngradient = nameIngradient;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
