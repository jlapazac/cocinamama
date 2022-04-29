package com.example.cocinamama.usecases.cart.recycler;

public class ProductoItem {
    private String desc;
    private int precio;

    public ProductoItem(String desc, int precio) {
        this.desc = desc;
        this.precio = precio;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
