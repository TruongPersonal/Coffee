package com.project.DTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class RecipeDTO {
    private int id;
    private String name_recipe;
    private String name_product;
    private String description;
    private Boolean is_active;
    private LocalDateTime createdAt;
    private ArrayList<DetailRecipeDTO> detailRecipe;
    private DetailRecipeDTO detailRecipe_1;

    public RecipeDTO() {

    }

    // updating
    public RecipeDTO(String name_recipe, String description, boolean is_active) {
        this.name_recipe = name_recipe;
        this.description = description;
        this.is_active = is_active;
    }

    // read
    public RecipeDTO(int id, String name_recipe, String description, boolean is_active, LocalDateTime createdAt) {
        this.id = id;
        this.name_recipe = name_recipe;
        this.description = description;
        this.is_active = is_active;
        this.createdAt = createdAt;
    }

    public RecipeDTO(String name_recipe, String description, DetailRecipeDTO detailRecipe_1) {
        this.name_recipe = name_recipe;
        this.description = description;
        this.detailRecipe_1 = detailRecipe_1;
    }

    public RecipeDTO(String name_recipe, String description, String name_product, ArrayList<DetailRecipeDTO> detailRecipe) {
        this.name_recipe = name_recipe;
        this.description = description;
        this.name_product = name_product;
        this.detailRecipe = detailRecipe;
    }

    // create
    public RecipeDTO(String name_recipe, String description) {
        this.name_recipe = name_recipe;
        this.description = description;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public int getId() {
        return id;
    }

    public String getName_recipe() {
        return name_recipe;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public void setName_recipe(String name_recipe) {
        this.name_recipe = name_recipe;
    }

    public ArrayList<DetailRecipeDTO> getDetailRecipe() {
        return detailRecipe;
    }

    public void setDetailRecipe(ArrayList<DetailRecipeDTO> detailRecipe) {
        this.detailRecipe = detailRecipe;
    }

    public DetailRecipeDTO getDetailRecipe_1() {
        return detailRecipe_1;
    }
    public void setDetailRecipe_1(DetailRecipeDTO detailRecipe_1) {
        this.detailRecipe_1 = detailRecipe_1;
    }
    

}
