package com.project.DTO;

import java.sql.Connection;

public class DetailEnterCouponDTO {
    private int id;
    private int coupon_id;
    private int ingredient_id;

    private String nameIngredient;
    private double price;
    private int quantity;
    private float totalPrice;

    public DetailEnterCouponDTO(int id, String nameIngredient, double price, int quantity, float totalPrice, int ingredient_id) {
        this.id = id;
        this.nameIngredient = nameIngredient;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.ingredient_id = ingredient_id;
    }

    public DetailEnterCouponDTO(int id, int coupon_id, int ingredient_id, int quantity, double price) {
        this.id = id;
        this.coupon_id = coupon_id;
        this.ingredient_id = ingredient_id;
        this.quantity = quantity;
        this.price = price;
    }

    public DetailEnterCouponDTO(int ingredient_id, int quantity, double price) {
        this.ingredient_id = ingredient_id;
        this.quantity = quantity;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCoupon_id(int coupon_id) {
        this.coupon_id = coupon_id;
    }

    public void setIngredient_id(int ingredient_id) {
        this.ingredient_id = ingredient_id;
    }

    public void setNameIngredient(String nameIngredient) {
        this.nameIngredient = nameIngredient;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public int getCoupon_id() {
        return coupon_id;
    }

    public int getIngredient_id() {
        return ingredient_id;
    }

    public String getNameIngredient() {
        return nameIngredient;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public static void create(Connection conn, Long enterCouponId, DetailEnterCouponDTO detailDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }
}
