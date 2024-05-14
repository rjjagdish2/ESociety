package com.example.esocietymanagement;

public class hallHelper {

    String reason,dec,year,month,day,name;

    public hallHelper(String reason, String dec, String year, String month, String day, String name) {
        this.reason = reason;
        this.dec = dec;
        this.year = year;
        this.month = month;
        this.day = day;
        this.name = name;
    }

    public hallHelper() {
    }

    public hallHelper(String date, String name, String reason) {

        this.day = date;
        this.name = name;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
