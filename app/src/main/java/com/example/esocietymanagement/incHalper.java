package com.example.esocietymanagement;

public class incHalper {

    String name,dec,year,month,day,amt;

    public incHalper(String day, String name, String amt) {

        this.day = day;
        this.name = name;
        this.amt = amt;
    }

    public incHalper(String day, String month, String year, String name, String amt) {
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.amt = amt;

    }

    public String getName() {
        return name;
    }

    public incHalper() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDec() {
        return dec;
    }

    public void setDec(String dec) {
        this.dec = dec;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getAmt() {
        return amt;
    }

    public void setAmt(String amt) {
        this.amt = amt;
    }

    public incHalper(String name, String dec, String year, String month, String day, String amt) {
        this.name = name;
        this.dec = dec;
        this.year = year;
        this.month = month;
        this.day = day;
        this.amt = amt;
    }
}
