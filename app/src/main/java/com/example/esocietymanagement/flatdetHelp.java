package com.example.esocietymanagement;

public class flatdetHelp {

    String plot,flatno,member,admin,mobile;


    public flatdetHelp(String scode, String flatno, String member, String yadmin, String mobileno) {

        this.plot = plot;
        this.flatno = flatno;
        this.member = member;
        this.admin=yadmin;
        this.mobile=mobileno;
    }



    public flatdetHelp(String flatno, String mobileno) {
        this.flatno=flatno;
        this.mobile=mobileno;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }


    public flatdetHelp() {
    }




    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
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
