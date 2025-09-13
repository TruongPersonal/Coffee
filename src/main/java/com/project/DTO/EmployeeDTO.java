package com.project.DTO;

import java.time.LocalDate;

public class EmployeeDTO {
    private int id;
    private String name;
    private String birthDate;
    private String address;
    private String position;
    private String phone;
    private float salary;
    private int account_id;

    public EmployeeDTO(int id, String name, int account_id) {
        this.id = id;
        this.name = name;
        this.account_id = account_id;
    }

    // detail account information
    public EmployeeDTO(String name, String birthDate, String address, String phone, String position, float salary) {
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
        this.position = position;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getAccount_id() {
        return account_id;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public String getPosition() {
        return position;
    }

    public float getSalary() {
        return salary;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
