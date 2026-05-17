package com.example.cenima_book_system.model;


public class Movie {
    private int movieId;
    private String title;
    private String genre;
    private int duration;

    public Movie(int movieId, String title, String genre, int duration) {
        this.movieId = movieId;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return title + " - " + genre + " - " + duration + " mins";
    }
}