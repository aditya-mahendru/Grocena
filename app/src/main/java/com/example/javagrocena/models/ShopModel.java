package com.example.javagrocena.models;

public class ShopModel {
    String Name;
    String Location;
    String img_url;

    public ShopModel() {
    }

    public ShopModel(String name, String location, String img_url) {
        Name = name;
        Location = location;
        this.img_url = img_url;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

}
