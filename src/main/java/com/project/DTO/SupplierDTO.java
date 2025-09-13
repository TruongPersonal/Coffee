package com.project.DTO;

import java.time.LocalDateTime;
import java.time.LocalDateTime;

public class SupplierDTO {
    int id;
    private String name_supplier;
    private String address;
    private String phone;
    private String email;
    private Boolean is_active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public SupplierDTO(){

    }

    // read
    public SupplierDTO(
            int id,
            String name_supplier,
            String address,
            String phone,
            String email,
            Boolean is_active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
        ) {
        this.id = id;
        this.name_supplier = name_supplier;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.is_active = is_active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // update
    public SupplierDTO(int id, String name_supplier, String address, String phone, String email) {
        this.id  = id;
        this.name_supplier = name_supplier;
        this.address = address;
        this.phone = phone;
        this.email = email;

    }

    // create
    public SupplierDTO(String name_supplier, String address, String phone, String email) {
        this.name_supplier = name_supplier;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName_supplier(String name_supplier) {
        this.name_supplier = name_supplier;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setupdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getName_supplier() {
        return name_supplier;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getupdatedAt() {
        return updatedAt;
    }

}
