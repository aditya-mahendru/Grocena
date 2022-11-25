package com.example.javagrocena.models;

public class CategoryModel {
    String Type;
    String url;

    public CategoryModel() {
    }

    public CategoryModel(String type, String url) {
        Type = type;
        this.url = url;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
