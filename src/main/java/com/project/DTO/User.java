package com.project.DTO;

import java.sql.Timestamp;

import org.apache.poi.xssf.usermodel.XSSFCell;

public class User {
    private int id;
    private String name;
    private String date;
    private String address;
    private String position;
    private String phone;
    private float salary;
    private Timestamp dateCreate;
    private int accountId;

    public User(int id, String name, String date, String address, String position, String phone, float salary,
            Timestamp dateCreate) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.address = address;
        this.position = position;
        this.phone = phone;
        this.salary = salary;
        this.dateCreate = dateCreate;
    }

    public User(int id, String name, String date, String address, String position, String phone, float salary) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.address = address;
        this.position = position;
        this.phone = phone;
        this.salary = salary;
    }

    public User() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;

    }

    public String toString() {
        return "Usee [id = " + id + ", name = " + name + ", address = " + address + ", position = " + position
                + ", phone = " + phone + ","
                + " salary = " + salary + ", accountId = " + accountId + "]";
    }
}
