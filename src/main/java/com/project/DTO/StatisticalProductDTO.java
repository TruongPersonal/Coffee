package com.project.DTO;

public class StatisticalProductDTO {
    private String name;
    private String image;
    private int quantity;
    private float price;
    private float dataStatistical;

    public StatisticalProductDTO(String name, String image, float price, float dataStatistical) {
        this.name = name;
        this.image = image;
        this.price = price;
        this.dataStatistical = dataStatistical;

    }

    public StatisticalProductDTO(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void setDataStatistical(float dataStatistical) {
        this.dataStatistical = dataStatistical;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getPrice() {
        return price;
    }

    public float getDataStatistical() {
        return dataStatistical;
    }

}
