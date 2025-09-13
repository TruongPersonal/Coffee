package com.project.DTO;

public class PaymentMethodDTO {
    private int id;
    private String payment_name;
    private String description;
    private int is_active;
    private String created_date;

    public PaymentMethodDTO(int id, String payment_name) {
        this.id = id;
        this.payment_name = payment_name;
    }

    public PaymentMethodDTO(int id, String payment_name, String description, int is_active, String created_date) {
        this.id = id;
        this.payment_name = payment_name;
        this.description = description;
        this.is_active = is_active;
        this.created_date = created_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPayment_name() {
        return payment_name;
    }

    public void setPayment_name(String payment_name) {
        this.payment_name = payment_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIs_active() {
        return is_active;
    }

    public void setIs_active(int is_active) {
        this.is_active = is_active;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

}
