package com.example.salonadmin;

import android.content.Intent;

public class MainModel {
    String salon_name,s_url,email,address,owner_name, phone_no;


    //zero arg constructor for firebase
    //default construtor with zero arguments
    MainModel(){

    }




    //constructor with arguments
    public MainModel(String salon_name, String s_url, String email, String address, String owner_name, String phone_no) {
        this.salon_name = salon_name;
        this.s_url = s_url;
        this.email = email;
        this.address = address;
        this.owner_name = owner_name;
        this.phone_no = phone_no;
    }

    public String getSalon_name() {

        return salon_name;
    }

    public void setSalon_name(String salon_name) {

        this.salon_name = salon_name;
    }

    public String getS_url() {

        return s_url;
    }

    public void setS_url(String s_url) {

        this.s_url = s_url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }
}

