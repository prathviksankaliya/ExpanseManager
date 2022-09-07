package com.itcraftsolution.expansemanager.Models;

public class Category {
    private String catName;
    private int catImg;

    public Category(int catImg, String catName) {
        this.catImg = catImg;
        this.catName = catName;
    }

    public int getCatImg() {
        return catImg;
    }

    public void setCatImg(int catImg) {
        this.catImg = catImg;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }
}
