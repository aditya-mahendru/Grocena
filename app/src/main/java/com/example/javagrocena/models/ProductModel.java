package com.example.javagrocena.models;

public class ProductModel {

    String Price;
    String Name;
    String URL;

    public ProductModel() {

    }

    public ProductModel(String price, String name, String URL) {
        Price = price;
        Name = name;
        this.URL = URL;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
