package com.project.DTO;

import java.time.LocalDateTime;

public class DeliveryBillDTO {
    private int id;
    private String nameDeliveryBill;
    private int accountId;
    private String nameEmployee;
    private LocalDateTime createdAt;
    private float amount;

    // get the delivery
    public DeliveryBillDTO(int id, String nameDeliveryBill, String nameEmployee, float amount,
            LocalDateTime createdAt) {
        this.id = id;
        this.nameDeliveryBill = nameDeliveryBill;
        this.nameEmployee = nameEmployee;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public DeliveryBillDTO(String nameDeliveryBill, int accountId) {
        this.nameDeliveryBill = nameDeliveryBill;
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameDeliveryBill() {
        return nameDeliveryBill;
    }

    public void setNameDeliveryBill(String nameDeliveryBill) {
        this.nameDeliveryBill = nameDeliveryBill;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getAmount() {
        return amount;
    }

}
