
package com.project.DTO;

import java.sql.Timestamp;

public class Supplier {
    private int id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private int isActive;
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
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getPhone()
    {
        return phone;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }
    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public int getIsActive()
    {
        return isActive;
    }
    public void setIsActive(int isActive)
    {
        this.isActive = isActive;
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
