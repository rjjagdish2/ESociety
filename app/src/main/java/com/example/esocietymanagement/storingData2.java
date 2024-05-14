package com.example.esocietymanagement;

import android.widget.EditText;

public class storingData2 {

    String plot;
    String society;
    String address;
    String city;
    String totflats;
    String totfloorflat;
    String scode;
    String floor;

    public storingData2(String plot, String society, String address, String city, String floor, String totfloorflat, String scode, String totflats) {
        this.plot = plot;
        this.society = society;
        this.address = address;
        this.city = city;
        this.totflats = totflats;
        this.totfloorflat = totfloorflat;
        this.scode = scode;
        this.floor = floor;
    }



    public storingData2(String sCode) {
        this.scode=sCode;
    }



    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getSociety() {
        return society;
    }

    public void setSociety(String society) {
        this.society = society;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTotflats() {
        return totflats;
    }

    public void setTotflats(String totflats) {
        this.totflats = totflats;
    }

    public String getTotfloorflat() {
        return totfloorflat;
    }

    public void setTotfloorflat(String totfloorflat) {
        this.totfloorflat = totfloorflat;
    }

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public storingData2() {
    }


}
