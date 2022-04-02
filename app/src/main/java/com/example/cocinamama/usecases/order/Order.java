package com.example.cocinamama.usecases.order;

public class Order {
    public int id;
    public int image;
    public String status;
    public String price;

    public Order(int id, int image, String status, String price) {
        this.id = id;
        this.image = image;
        this.status = status;
        this.price = price;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
