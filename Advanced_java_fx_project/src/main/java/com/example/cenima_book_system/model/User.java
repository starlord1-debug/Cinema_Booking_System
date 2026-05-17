package com.example.cenima_book_system.model;


public class User {
    private int userId;
    private String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserType() {
        return "Regular User";
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}