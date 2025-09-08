package com.project.BUS;

import java.util.ArrayList;

import com.project.DAO.CategoryDAO;
import com.project.DTO.CategoryDTO;

public class CategoryBUS {
    private CategoryDAO categoryDAO;

    public CategoryBUS() {
        this.categoryDAO = new CategoryDAO();
    }

    public ArrayList<CategoryDTO> getAll() {
        ArrayList<CategoryDTO> categories = null;
        try {
            categories = categoryDAO.getAll();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return categories;
    }

    public CategoryDTO getCategoryByID(int search_id) {
        CategoryDTO category = null;
        try {
            category = categoryDAO.getCategoryByID(search_id);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return category;
    }

}
