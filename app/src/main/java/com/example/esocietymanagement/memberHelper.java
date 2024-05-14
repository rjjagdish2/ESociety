package com.example.esocietymanagement;

public class memberHelper {

    String flatno,name;

    public memberHelper(String flatno, String name) {
        this.flatno = flatno;
        this.name = name;
    }

    public memberHelper() {
    }

    public String getFlatno() {
        return flatno;
    }

    public void setFlatno(String flatno) {
        this.flatno = flatno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
