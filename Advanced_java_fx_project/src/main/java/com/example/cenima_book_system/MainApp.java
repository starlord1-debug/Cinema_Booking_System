package com.example.cenima_book_system;

import com.example.cenima_book_system.database.BookingDAO;
import com.example.cenima_book_system.exception.SeatAlreadyBookedException;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.concurrent.Task;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) {

        BookingDAO dao = new BookingDAO();

        Label titleLabel = new Label("Cinema Booking System");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label nameLabel = new Label("User Name:");
        Label movieLabel = new Label("Movie:");
        Label timeLabel = new Label("Show Time:");
        Label seatLabel = new Label("Seat Number:");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        ComboBox<String> movieCombo = new ComboBox<>();
        movieCombo.getItems().addAll("Interstellar", "The Batman", "Inception");
        movieCombo.setPromptText("Select movie");

        ComboBox<String> timeCombo = new ComboBox<>();
        timeCombo.setPromptText("Select time");

        movieCombo.setOnAction(e -> {
            timeCombo.getItems().clear();

            String movie = movieCombo.getValue();

            if (movie.equals("Interstellar")) {
                timeCombo.getItems().addAll("6:00 PM", "9:00 PM");
            } else if (movie.equals("The Batman")) {
                timeCombo.getItems().add("7:00 PM");
            } else if (movie.equals("Inception")) {
                timeCombo.getItems().add("10:00 PM");
            }
        });

        TextField seatField = new TextField();
        seatField.setPromptText("Enter seat number");

        Button bookButton = new Button("Book Ticket");

        Label messageLabel = new Label();

        bookButton.setOnAction(e -> {

            String userName = nameField.getText();
            String movie = movieCombo.getValue();
            String time = timeCombo.getValue();

            int seatNumber = Integer.parseInt(seatField.getText());
            int showtimeId = getShowtimeId(movie, time);

            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws Exception {

                    if (dao.isSeatBooked(showtimeId, seatNumber)) {
                        throw new SeatAlreadyBookedException("Seat already booked");                    }

                    dao.addBooking(userName, showtimeId, seatNumber);

                    return null;
                }
            };

            task.setOnSucceeded(event -> {
                messageLabel.setText("Booking saved successfully.");

                nameField.clear();
                movieCombo.setValue(null);
                timeCombo.getItems().clear();
                seatField.clear();
            });

            task.setOnFailed(event -> {
                messageLabel.setText("Seat already booked or invalid data.");
            });

            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
        });
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(12);
        grid.setHgap(10);

        grid.add(titleLabel, 0, 0, 2, 1);

        grid.add(nameLabel, 0, 1);
        grid.add(nameField, 1, 1);

        grid.add(movieLabel, 0, 2);
        grid.add(movieCombo, 1, 2);

        grid.add(timeLabel, 0, 3);
        grid.add(timeCombo, 1, 3);

        grid.add(seatLabel, 0, 4);
        grid.add(seatField, 1, 4);

        grid.add(bookButton, 1, 5);
        grid.add(messageLabel, 1, 6);

        Scene scene = new Scene(grid, 430, 300);

        stage.setTitle("Cinema Booking System");
        stage.setScene(scene);
        stage.show();
    }

    private int getShowtimeId(String movie, String time) {

        if (movie.equals("Interstellar") && time.equals("6:00 PM")) {
            return 1;
        }

        if (movie.equals("Interstellar") && time.equals("9:00 PM")) {
            return 2;
        }

        if (movie.equals("The Batman") && time.equals("7:00 PM")) {
            return 3;
        }

        if (movie.equals("Inception") && time.equals("10:00 PM")) {
            return 4;
        }

        return 0;
    }

    public static void main(String[] args) {
        launch(args);
    }
}