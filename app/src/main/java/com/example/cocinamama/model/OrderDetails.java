package com.example.cocinamama.model;

public class OrderDetails {
    public int id;
    public int image;
    public String title;
    public String typeOrder;
    public String price;
    public int amount;

    public OrderDetails(int id, int image, String title, String typeOrder, String price, int amount) {
        this.id = id;
        this.image = image;
        this.title = title;
        this.typeOrder = typeOrder;
        this.price = price;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeOrder() {
        return typeOrder;
    }

    public void setTypeOrder(String typeOrder) {
        this.typeOrder = typeOrder;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
