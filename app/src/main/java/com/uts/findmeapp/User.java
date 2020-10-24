package com.uts.findmeapp;

public class User {
    private String id;
    private String Name;
    private String Username;
    private String Email;
    private String Password;

    public User(String id,String Name, String Username, String Email, String Password){
        this.id = id;
        this.Name = Name;
        this.Username = Username;
        this.Email = Email;
        this.Password = Password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
