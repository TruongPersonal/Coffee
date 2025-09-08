package com.project.DTO;

import java.time.LocalDateTime;
import java.time.LocalDateTime;

public class DetailRecipeDTO {
    private int id;
    private int id_ingredient;
    private int id_product;
    private int id_recipe;

    private String name_product;
    private String name_ingredient;
    private int quantity;
    private String unit;

    public DetailRecipeDTO(int id, int id_product, int quantity) {
        this.id = id;
        this.id_product = id_product;
        this.quantity = quantity;
    }

    public DetailRecipeDTO(String name_ingredient, String unit, int quantity) {
        this.name_ingredient = name_ingredient;
        this.unit = unit;
        this.quantity = quantity;
    }

    public DetailRecipeDTO(int id_product, int id_ingredient, int id_recipe, String unit, int quantity) {
        this.id_product = id_product;
        this.id_ingredient = id_ingredient;
        this.id_recipe = id_recipe;
        this.unit = unit;
        this.quantity = quantity;
    }

    public DetailRecipeDTO(int id, int id_product, int id_ingredient, int id_recipe, String unit, int quantity) {
        this.id = id;
        this.id_product = id_product;
        this.id_ingredient = id_ingredient;
        this.id_recipe = id_recipe;
        this.unit = unit;
        this.quantity = quantity;
    }

    public DetailRecipeDTO(String name_product, String name_ingredient, int quantity, String unit) {
        this.name_product = name_product;
        this.name_ingredient = name_ingredient;
        this.quantity = quantity;
        this.unit = unit;

    }

    public DetailRecipeDTO(String name_ingredient, int quantity, String unit) {
        this.name_ingredient = name_ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getName_ingredient() {
        return name_ingredient;
    }

    public void setName_ingredient(String name_ingredient) {
        this.name_ingredient = name_ingredient;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getId() {
        return id;
    }

    public int getId_ingredient() {
        return id_ingredient;
    }

    public int getId_product() {
        return id_product;
    }

    public int getId_recipe() {
        return id_recipe;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setId_ingredient(int id_ingredient) {
        this.id_ingredient = id_ingredient;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public void setId_recipe(int id_recipe) {
        this.id_recipe = id_recipe;
    }

}
