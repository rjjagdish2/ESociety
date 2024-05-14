package com.example.esocietymanagement;

public class mainHelper {

    String title,day,amt,month,year;

    public mainHelper(String title, String day, String amt, String month, String year) {
        this.title = title;
        this.day = day;
        this.amt = amt;
        this.month = month;
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public mainHelper(String title, String day, String amt) {
        this.title = title;
        this.day = day;
        this.amt = amt;
    }

    public mainHelper() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
