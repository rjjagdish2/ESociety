package com.example.esocietymanagement;

public class storingData {

    String fname,lname,mono,password;

    public storingData(String fname, String lname, String mono, String password) {
        this.fname = fname;
        this.lname = lname;
        this.mono = mono;
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMono() {
        return mono;
    }

    public void setMono() {
        this.mono = mono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
