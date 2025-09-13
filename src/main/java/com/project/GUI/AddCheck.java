package com.project.GUI;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AddCheck {
    public int id;
    public String product;
    public Float price;
    public int hd;
    public int sl;
    public String date;
    public int lsp;
    public String img_path;
    


    public AddCheck(){}
    public AddCheck(int id, String product, Float price, int hd, int sl, String date, int lsp,String img_path) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.hd = hd;
        this.sl = sl;
        this.date = date;
        this.lsp = lsp;
        this.img_path = img_path;


    }

    public int getId() {
        return id;
    }
    public String getImg() {
        return img_path;
    }
    public Float getPrice() {
        return price;
    }
    public String getProduct() {
        return product;
    }
    public int getHoatdong() {
        return hd;
    }
    public int getLoaisp() {
        return lsp;
    }
    public String getNgaylap() {
        return date;
    }
    public int getSoluong() {
        return sl;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setImg(String img_path) {
        this.img_path = img_path;
    }
    public void setPrice(Float price) {
        this.price = price;
    }
    public void setProduct(String product) {
        this.product = product;
    }
    public void setHoatdong(int hd) {
        this.hd = hd;
    }
    public void setLoaisp(int lsp) {
        this.lsp = lsp;
    }
    public void setNgaylap(String date) {
        this.date = date;
    }
    public void setSoluong(int sl) {
        this.sl = sl;
    }

    public void displayImg(String imgpath, JLabel label) {
        ImageIcon imgIc =new ImageIcon(imgpath);
        if (label.getWidth() > 0 && label.getHeight() > 0) {
            Image img = imgIc.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        } else {
            // Nếu kích thước của JLabel là 0, sử dụng kích thước mặc định
            Image img = imgIc.getImage().getScaledInstance(170, 100, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(img));
        }
        }
    public static void main(String[] args) {
        new AddCheck();
        
        
     }
}
