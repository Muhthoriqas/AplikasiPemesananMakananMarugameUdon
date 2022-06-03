package com.example.marugame.Model;

public class Food {
    private String Name,Image,Description,Price,Discount,MenuId;

    public Food(){

    }
    public Food(String name, String image, String description, String price,String discount,String menuId){
        Name = name;
        Image=image;
        Description=description;
        Price=price;
        Discount=discount;
        MenuId=menuId;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getImage() {
        return Image;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDescription() {
        return Description;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getPrice() {
        return Price;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getDiscount() {
        return Discount;
    }


    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public String getMenuId() {
        return MenuId;
    }
}
