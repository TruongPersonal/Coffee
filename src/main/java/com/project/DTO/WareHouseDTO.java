
package com.project.DTO;

import java.time.LocalDateTime;

public class WareHouseDTO {
    private int id;
    private String name;
    private String unit;
    private int quantity;
    private int is_active;
    private LocalDateTime create_date;
    private LocalDateTime update_date;

    public WareHouseDTO(int id, String name, int quantity) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setString(String unit) {
        this.unit = unit;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIsActive() {
        return is_active;
    }

    public void setISAcctive(int is_active) {
        this.is_active = is_active;
    }

    public LocalDateTime getCreateDate() {
        return create_date;
    }

    public void setCreateDate(LocalDateTime create_date) {
        this.create_date = create_date;
    }

    public void setUpdateDate(LocalDateTime update_date) {
        this.update_date = update_date;
    }

    public LocalDateTime getUpdateDate() {
        return update_date;
    }

    public static void main(String[] args) {
        double number = -10.5; // Ví dụ số âm

        if (number < 0) {
            System.out.println("Số âm");
        } else {
            System.out.println("Không phải số âm");
        }

    }
}