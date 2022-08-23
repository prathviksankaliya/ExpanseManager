package com.itcraftsolution.expansemanager.Models;

public class LatestEntry {

    private String  category, currency, price, dateMonth, year, time;
    private int img;
    public LatestEntry(int img, String category, String currency, String price, String dateTime, String year, String time) {
        this.img = img;
        this.category = category;
        this.currency = currency;
        this.price = price;
        this.dateMonth = dateTime;
        this.year = year;
        this.time = time;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDateMonth() {
        return dateMonth;
    }

    public void setDateMonth(String dateMonth) {
        this.dateMonth = dateMonth;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
