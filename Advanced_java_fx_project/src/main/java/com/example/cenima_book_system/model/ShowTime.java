package com.example.cenima_book_system.model;

public class ShowTime {
    private int showTimeId;
    private int movieId;
    private String time;
    private int availableSeats;

    public ShowTime(int showTimeId, int movieId, String time, int availableSeats) {
        this.showTimeId = showTimeId;
        this.movieId = movieId;
        this.time = time;
        this.availableSeats = availableSeats;
    }

    public int getShowTimeId() {
        return showTimeId;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getTime() {
        return time;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    @Override
    public String toString() {
        return time + " | Seats: " + availableSeats;
    }
}