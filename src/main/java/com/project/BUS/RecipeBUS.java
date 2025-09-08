package com.project.BUS;

import java.util.ArrayList;
import java.util.HashMap;

import com.project.DAO.ProductDAO;
import com.project.DAO.RecipeDAO;
import com.project.DAO.SupplierDAO;
import com.project.DTO.DetailRecipeDTO;
import com.project.DTO.ProductDTO;
import com.project.DTO.RecipeDTO;

public class RecipeBUS {

    public static HashMap<Boolean, String> createdRecipe(RecipeDTO recipe,
            ArrayList<DetailRecipeDTO> list_new_detail_recipe) {
        HashMap<Boolean, String> respon = new HashMap<Boolean, String>();
        try {
            System.out.println(recipe.getName_recipe());
            int id_recipe = RecipeDAO.createRecipe(recipe);

            if (id_recipe == -1) {
                respon.put(false, "Tạo mới công thức thất bại!");
                return respon;

            }

            for (DetailRecipeDTO detailRecipeDTO : list_new_detail_recipe) {

                detailRecipeDTO.setId_recipe(id_recipe);

                RecipeDAO.createDetailRecipe(detailRecipeDTO);

                // System.out.println("id product: " + detailRecipeDTO.getId_product());
                // System.out.println("id recipe: " + detailRecipeDTO.getId_recipe());
                // System.out.println("id ingredient: " + detailRecipeDTO.getId_ingredient());
                // System.out.println("unit: " + detailRecipeDTO.getUnit());
                // System.out.println("quantity: " + detailRecipeDTO.getQuantity());
            }
            respon.put(true, "Tạo mới công thức thành công!");

            return respon;

        } catch (Exception e) {
            return respon;
        }
    }

    public static ArrayList<RecipeDTO> get_all_recipe() {
        try {
            ArrayList<RecipeDTO> list_recipe = RecipeDAO.get_all_recipe();
            if (list_recipe == null) {
                return null;
            }
            return list_recipe;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }

    public static HashMap<Boolean, String> updateRecipeBUS(ArrayList<DetailRecipeDTO> list_new_detail_recipe) {
        HashMap<Boolean, String> result = new HashMap<Boolean, String>();
        try {

            boolean skipFirstIteration = true;
            for (DetailRecipeDTO detailRecipeDTO : list_new_detail_recipe) {
                if (skipFirstIteration) {
                    skipFirstIteration = false;
                    continue;
                }
                int product_id = detailRecipeDTO.getId_product();
                ProductDTO quantityProduct = ProductDAO.getProductById(product_id);

                if (quantityProduct.getQuantity() != 0) {
                    // System.out.println("check quantity product: " + product_id);
                    // System.out.println("check quantity product: " + quantityProduct.getProduct_name());
                    // System.out.println("check quantity product: " + quantityProduct.getQuantity());
                    result.put(false,
                            "Công thức đang được sử dụng bởi sản phẩm còn số lượng bán ra. không được chỉnh sửa");
                    return result;
                }

                // Thêm chi tiết mới vào DB
                boolean check = RecipeDAO.updateDetailRecipe(detailRecipeDTO);
                if (!check) {
                    result.put(
                            false, "Chỉnh sửa thất bại");
                    return result;
                }

            }
            result.put(true, "Chỉnh sửa công thức thành công.");
            return result;
        } catch (Exception e) {
            result.put(false, "Có lỗi xảy ra. Chỉnh sửa công thức thất bại");
            return result;
        }
    }

    public static HashMap<Boolean, String> removeRecipe(int id_recipe) {
        HashMap<Boolean, String> res = new HashMap<Boolean, String>();
        try {
            boolean check = false;

            // Kiểm tra xem có sản phẩm nào đang được sử dụng bởi công thức đó
            ArrayList<DetailRecipeDTO> recipeDTO = RecipeDAO.getDetailRecipeById(id_recipe);
            if (recipeDTO == null) {
                res.put(false, "Công thức này không tồn tại.");
                return res;
            }

            for (DetailRecipeDTO detailRecipeDTO : recipeDTO) {
                if (detailRecipeDTO.getQuantity() > 0) {
                    res.put(false, "Sản phẩm chứa công thức này đang còn nguyên liệu không thể xoá.");
                    return res;

                } else {
                    // Xoá chi tiết công thức từ DB
                    check = RecipeDAO.deleteDetailRecipe(detailRecipeDTO.getId());
                    RecipeDAO.ChangeIsActiveRecipe(id_recipe, false);

                }
            }

            if (check) {
                res.put(true, "xoá công thức thành công");
            } else {
                res.put(false, "xoá công thức thất bại");
            }

            return res;
        } catch (Exception e) {
            res.put(false, "Có lỗi xảy ra.");
            return res;
        }
    }

    public static ArrayList<RecipeDTO> searchRecipeByNameBUS(String search_supplier) {
        try {
            ArrayList<RecipeDTO> list_recipe = RecipeDAO.SearchRecipeByName(search_supplier);
            if (list_recipe == null) {
                return null;
            }
            return list_recipe;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }

    }

    public static RecipeDTO detailRecipeByIdBUS(int id) {
        try {
            // Lấy danh sách các công thức từ DAO
            ArrayList<RecipeDTO> list_recipe = RecipeDAO.getRecipeById(id);

            // Khởi tạo danh sách các thành phần
            ArrayList<DetailRecipeDTO> list_ingredient = new ArrayList<>();

            // Nếu không tìm thấy công thức nào
            if (list_recipe == null || list_recipe.isEmpty()) {
                return null;
            }

            // Duyệt qua danh sách công thức để thêm các thành phần vào list_ingredient
            for (RecipeDTO recipeDTO : list_recipe) {
                list_ingredient.add(
                        new DetailRecipeDTO(recipeDTO.getDetailRecipe_1().getName_ingredient(),
                                recipeDTO.getDetailRecipe_1().getQuantity(), recipeDTO.getDetailRecipe_1().getUnit()));
            }

            // Tạo và trả về công thức đầu tiên từ danh sách
            RecipeDTO firstRecipe = list_recipe.get(0);
            RecipeDTO data_return = new RecipeDTO(
                    firstRecipe.getName_recipe(),
                    firstRecipe.getDescription(),
                    firstRecipe.getDetailRecipe_1().getName_product(),
                    list_ingredient);

            return data_return;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // getRecipeByProductId
    public static ArrayList<DetailRecipeDTO> getRecipeByProductId(int id_product) {
        try {
            ArrayList<DetailRecipeDTO> list_recipe = RecipeDAO.getRecipeByProductId(id_product);
            if (list_recipe == null) {
                return null;
            }
            return list_recipe;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }
    public static ArrayList<DetailRecipeDTO> getDetailRecipeByProductId(int id_product) {
        try {
            ArrayList<DetailRecipeDTO> list_recipe = RecipeDAO.getDetailRecipeByIdProduct(id_product);
            if (list_recipe == null) {
                return null;
            }
            return list_recipe;
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
    }

    public static void main(String[] args) {
        // print getRecipeByProductId
        ArrayList<DetailRecipeDTO> data = getDetailRecipeByProductId(1);

        for (DetailRecipeDTO detailRecipeDTO : data) {
            System.out.println(detailRecipeDTO.getName_ingredient());
            System.out.println(detailRecipeDTO.getQuantity());
            System.out.println(detailRecipeDTO.getUnit());
        }
       

    }

}
