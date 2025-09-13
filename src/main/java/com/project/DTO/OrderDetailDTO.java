package com.project.DTO;

public class OrderDetailDTO {
    private int id;
    private int quantity;
    private int product_id;
    private int order_id;
    
    public OrderDetailDTO(int quantity, int product_id) {
        this.quantity = quantity;
        this.product_id = product_id;
    }

    public OrderDetailDTO(int id, int quantity, int product_id, int order_id) {
        this.id = id;
        this.quantity = quantity;
        this.product_id = product_id;
        this.order_id = order_id;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getProduct_id() {
        return product_id;
    }
    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }
    public int getOrder_id() {
        return order_id;
    }
    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

}
