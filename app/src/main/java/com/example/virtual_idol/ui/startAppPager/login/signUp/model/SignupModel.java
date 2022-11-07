package com.example.virtual_idol.ui.startAppPager.login.signUp.model;

public class SignupModel {
    String ID, PASSWORD, E_MAIL;

    public SignupModel(String ID, String PASSWORD, String e_MAIL) {
        this.ID = ID;
        this.PASSWORD = PASSWORD;
        E_MAIL = e_MAIL;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getE_MAIL() {
        return E_MAIL;
    }

    public void setE_MAIL(String e_MAIL) {
        E_MAIL = e_MAIL;
    }
}
