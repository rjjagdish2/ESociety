package com.example.esocietymanagement;

public class gardenHelper {
    String name,price,dec;

    public gardenHelper(String name, String price, String dec) {
        this.name = name;
        this.price = price;
        this.dec = dec;
    }

    public gardenHelper() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }
}
