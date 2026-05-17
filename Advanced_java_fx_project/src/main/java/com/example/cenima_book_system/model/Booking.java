package com.example.cenima_book_system.model;

public class Booking {
    private int bookingId;
    private String userName;
    private String movieTitle;
    private String showTime;
    private int seatNumber;

    public Booking(int bookingId, String userName, String movieTitle, String showTime, int seatNumber) {
        this.bookingId = bookingId;
        this.userName = userName;
        this.movieTitle = movieTitle;
        this.showTime = showTime;
        this.seatNumber = seatNumber;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getUserName() {
        return userName;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getShowTime() {
        return showTime;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
}