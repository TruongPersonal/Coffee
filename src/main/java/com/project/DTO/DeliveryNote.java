package com.project.DTO;

import java.sql.Timestamp;

public class DeliveryNote {
    private int id;
    private String name;
    private int id_account;
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
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getIdAccount()
    {
        return id_account;
    }
    public void setIdAccount(int id_account)
    {
        this.id_account = id_account;
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
