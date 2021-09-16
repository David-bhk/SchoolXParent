package com.example.drawer.AdminPanel;

public class UserModel {
    String email;
    String profile;
    String username;

    public UserModel() {
    }

    public UserModel(String email, String profile, String username) {
        this.email = email;
        this.profile = profile;
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
