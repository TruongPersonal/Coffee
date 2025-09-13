
package com.project.DTO;

import java.sql.Timestamp;

public class DetailReceipt {
    private int id;
    private int idReceipt;
    private int idIngredient;
    private float quantity;
    private String unit;
    private float price;
    private Timestamp createDate;
    private Timestamp updateDate;
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public int getIdReceipt()
    {
        return idReceipt;
    }
    public void setIdReceipt(int idReceipt)
    {
        this.idReceipt = idReceipt;
    }
    public int getIdIngredient()
    {
        return idIngredient;
    }
    public void setIdIngredient(int idIngredient)
    {
        this.idIngredient = idIngredient;
    }
    public float getQuantity()
    {
        return quantity;
    }
    public void setQuantity(float quantity)
    {
        this.quantity = quantity;
    }
    public String getUnit()
    {
        return unit;
    }
    public void setUnit(String unit)
    {
        this.unit = unit;
    }
    public float getPrice()
    {
        return price;
    }
    public void setPrice(float price)
    {
        this.price = price;
    }
      public Timestamp getCreateDate()
    {
        return createDate;
    }
    public void setCreateDate(Timestamp createDate)
    {
        this.createDate = createDate;
    }
    public Timestamp getUpdateDate()
    {
        return updateDate;
    }
    public void setUpdateDate(Timestamp updateDate)
    {
        this.updateDate = updateDate;
    }
}
