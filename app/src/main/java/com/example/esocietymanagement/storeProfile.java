package com.example.esocietymanagement;

public class storeProfile {
    String flatno,member,scode;

    public storeProfile(String flatno, String member, String scode) {
        this.flatno = flatno;
        this.member = member;
        this.scode = scode;
    }

    public storeProfile(String setFlat) {
        this.flatno=setFlat;
    }

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    public storeProfile() {
    }

    public String getFlatno() {
        return flatno;
    }

    public void setFlatno(String flatno) {
        this.flatno = flatno;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }
}
