package com.project.BUS;

import java.util.ArrayList;

import com.project.DAO.CartDAO;
import com.project.DTO.CartDTO;

public class CartBUS {

    public static boolean addCartBUS(CartDTO cart) {
        return CartDAO.insertCart(cart);
    }

    public static ArrayList<CartDTO> getCartByAccountId(int accountId) {
        return CartDAO.getCartByAccountId(accountId);
    }

    public static CartDTO getCartByProductAndAccountId(int productId, int accountId) {
        return CartDAO.getCartByProductId(productId, accountId);
    }

}
