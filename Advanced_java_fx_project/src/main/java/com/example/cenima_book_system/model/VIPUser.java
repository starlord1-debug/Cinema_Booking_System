package com.example.cenima_book_system.model;



public class VIPUser extends com.example.cenima_book_system.model.User {

    public VIPUser(int userId, String name) {
        super(userId, name);
    }

    @Override
    public String getUserType() { //polymo(override)
        return "VIP User";
    }
}


