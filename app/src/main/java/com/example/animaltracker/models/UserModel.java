package com.example.animaltracker.models;

public class UserModel {
    //prams
        private String email;
        private String password;
        private String phone;
        private String name;

    //constructor full
    public UserModel(String email, String password, String phone, String name) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.name = name;
    }

    //constructor empty
    public UserModel() {
    }

    //get func
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    //set func
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }
}
