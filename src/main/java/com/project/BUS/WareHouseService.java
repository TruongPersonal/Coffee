
package com.project.BUS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.project.DAO.RecipeDAO;
import com.project.DAO.WareHouseDAO;
import com.project.DTO.DetailRecipeDTO;
import com.project.DTO.WareHouse;

public class WareHouseService {
    WareHouseDAO warehousedao;

    public WareHouseService() {
        warehousedao = new WareHouseDAO();
    }

    public List<WareHouse> getAllWareHouse() throws Exception {
        return warehousedao.getAllWareHouse();
    }

    public boolean AddWareHouse(WareHouse warehouse) throws Exception {
        return warehousedao.AddWareHouse(warehouse);
    }

    public void updateWareHouse(WareHouse warehouse) throws Exception {
        warehousedao.UpdateWareHouse(warehouse);
    }

    public WareHouse getNameWareHouse(String name) throws Exception {
        return warehousedao.getNameWareHouse(name);
    }

    public Map<Boolean, String> deleteWareHouse(int id) throws Exception {
        Map<Boolean, String> respon = new HashMap<>();
        WareHouse ingredient = warehousedao.getIdWareHouse(id);
        int quantity = ingredient.getQuantity();
        if (quantity > 0) {
            respon.put(false, "Nguyên liệu đang còn tồn kho không thể xoá.");
            return respon;
        }

        ArrayList<DetailRecipeDTO> check_recipe = RecipeDAO.getRecipeByIngredientId(id);
        if (check_recipe.size() > 0) {
            respon.put(false, "Nguyên liệu đang có công thức này đang sử dụng không thể xoá.");
            return respon;
        }

        warehousedao.deleteWareHouse(id);
        respon.put(true, "Xoá nguyên liệu thành công.");
        return respon;
    }

    public List<WareHouse> searchAllWareHouseById(int id) throws Exception {
        return warehousedao.searchAllWareHouseById(id);
    }

    public List<WareHouse> searchAllWareHouseByName(String name) throws Exception {
        return warehousedao.searchAllWareHouseByName(name);
    }

    public WareHouse getIdWareHouse(int id) throws Exception {
        return warehousedao.getIdWareHouse(id);
    }

    public void updateNameWareHouse(WareHouse warehouse) throws Exception {
        warehousedao.updateNameWareHouse(warehouse);
    }

    public boolean WareHouseExist(String name) throws Exception {
        return warehousedao.WareHouseExist(name);
    }
}
