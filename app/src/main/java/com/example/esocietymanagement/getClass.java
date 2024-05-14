package com.example.esocietymanagement;

public class getClass {
    String name,plotNo,city;

    public getClass(String name, String plotNo, String city) {
        this.name = name;
        this.plotNo = plotNo;
        this.city = city;
    }

    public getClass() {
    }

    public String getName() {
        return name;
    }

    public String getPlotNo() {
        return plotNo;
    }

    public String getCity() {
        return city;
    }
}
