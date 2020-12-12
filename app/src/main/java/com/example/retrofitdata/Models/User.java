package com.example.retrofitdata.Models;

public class User {

    private String Id;
    private String Email;
    private int Password;
    private String Name;

    public User(String id, String email, int password, String name) {
        Id = id;
        Email = email;
        Password = password;
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getPassword() {
        return Password;
    }

    public void setPassword(int password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
