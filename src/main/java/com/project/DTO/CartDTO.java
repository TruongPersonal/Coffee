package com.project.DTO;

public class CartDTO {
    private int id;
    private int quantity;
    private int productId;
    private int accountId;
    private ProductDTO productDTO;

    public CartDTO(int id, int quantity, int productId, int accountId) {
        this.id = id;
        this.quantity = quantity;
        this.productId = productId;
        this.accountId = accountId;

    }

    public CartDTO(int productId, int quantity, int accountId) {
        this.productId = productId;
        this.quantity = quantity;
        this.accountId = accountId;
    }

    public CartDTO(int id, int quantity, ProductDTO productDTO, int accountId) {
        this.id = id;
        this.quantity = quantity;
        this.productDTO = productDTO;
        this.accountId = accountId;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getProductId() {
        return productId;
    }

    public int getAccountId() {
        return accountId;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

}
