package com.example.temodemo;

import java.io.Serializable;

public class GardBean implements Serializable {
    private int image;//图片ID
    private String name;//图片名字

    public GardBean() {
    }

    public GardBean(int image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
