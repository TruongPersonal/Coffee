
package com.project.DTO;

import java.sql.Timestamp;

public class DetailDeliveryNote {
    private int id;
    private int idDeliveryNote;
    private int idIngredient;
    private float quantity;
    private String unit;
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
    public int getIdDeliveryNote()
    {
        return idDeliveryNote;
    }
    public void setIdDeliveryNote(int idDeliveryNote)
    {
        this.idDeliveryNote = idDeliveryNote;
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
