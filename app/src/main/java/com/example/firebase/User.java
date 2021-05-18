package com.example.firebase;

public class User {
    String name;
    String phone;

    public User(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public User() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

}
