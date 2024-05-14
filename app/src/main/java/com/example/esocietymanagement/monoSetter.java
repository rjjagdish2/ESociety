package com.example.esocietymanagement;

public class monoSetter {

    String mono,password;

    public monoSetter(String mono, String password) {
        this.mono = mono;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public monoSetter() {
    }

    public String getMono() {
        return mono;
    }

    public void setMono(String mono) {
        this.mono = mono;
    }
}
