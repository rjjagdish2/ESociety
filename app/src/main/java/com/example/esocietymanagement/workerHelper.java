package com.example.esocietymanagement;

public class workerHelper {
    String name,mobile,profession;

    public workerHelper(String name, String mobile, String profession) {
        this.name = name;
        this.mobile = mobile;
        this.profession = profession;
    }

    public workerHelper() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
