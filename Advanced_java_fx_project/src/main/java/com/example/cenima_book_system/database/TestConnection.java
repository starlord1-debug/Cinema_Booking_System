package com.example.cenima_book_system.database;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        try {

            Connection conn = DBConnection.getConnection();

            if (conn != null) {

                System.out.println("Connected Successfully!");
            }

            conn.close();

        } catch (Exception e) {

            System.out.println("Connection Failed: " + e.getMessage());
        }
    }
}