package com.project.DTO;

public class ReceiptDTO {
    private int id;
    private String name_receipt;
    private int account_id;
    private int supplier_id;
    private String name_supplier;
    private String name_employee;
    private String createdAt;
    private String updatedAt;
    
    public ReceiptDTO(

            int id,
            String name_receipt,
            int account_id,
            int supplier_id,
            String name_supplier,
            String name_employee,
            String createdAt,
            String updatedAt) {
        this.id = id;
        this.name_receipt = name_receipt;
        this.account_id = account_id;
        this.supplier_id = supplier_id;
        this.name_supplier = name_supplier;
        this.name_employee = name_employee;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public ReceiptDTO(
            String name_receipt,
            int account_id,
            int supplier_id,
            String updatedAt) {
        this.name_receipt = name_receipt;
        this.account_id = account_id;
        this.supplier_id = supplier_id;
        this.updatedAt = updatedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName_receipt(String name_receipt) {
        this.name_receipt = name_receipt;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public void setName_supplier(String name_supplier) {
        this.name_supplier = name_supplier;
    }

    public void setName_employee(String name_employee) {
        this.name_employee = name_employee;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getName_receipt() {
        return name_receipt;
    }

    public int getAccount_id() {
        return account_id;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public String getName_employee() {
        return name_employee;
    }

    public String getName_supplier() {
        return name_supplier;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

}
