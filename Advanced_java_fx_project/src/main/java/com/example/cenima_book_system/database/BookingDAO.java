package com.example.cenima_book_system.database;

import java.sql.*;

public class BookingDAO {

    public void addBooking(String userName, int showtimeId, int seatNumber) throws SQLException {

        Connection conn = DBConnection.getConnection();

        int userId = getUserId(conn, userName);

        String sql = "INSERT INTO bookings (user_id, showtime_id, seat_number) VALUES (?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, userId);
        stmt.setInt(2, showtimeId);
        stmt.setInt(3, seatNumber);

        stmt.executeUpdate();

        stmt.close();
        conn.close();
    }

    private int getUserId(Connection conn, String userName) throws SQLException {

        String sql = "SELECT user_id FROM users WHERE name = ?";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, userName);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int userId = rs.getInt("user_id");

            rs.close();
            stmt.close();

            return userId;
        }

        rs.close();
        stmt.close();

        String insertSql = "INSERT INTO users (name, user_type) VALUES (?, ?)";

        PreparedStatement insertStmt = conn.prepareStatement(
                insertSql,
                Statement.RETURN_GENERATED_KEYS
        );

        insertStmt.setString(1, userName);
        insertStmt.setString(2, "Regular");

        insertStmt.executeUpdate();

        ResultSet keys = insertStmt.getGeneratedKeys();

        int newUserId = 0;

        if (keys.next()) {
            newUserId = keys.getInt(1);
        }

        keys.close();
        insertStmt.close();

        return newUserId;
    }

    public boolean isSeatBooked(int showtimeId, int seatNumber) throws SQLException {

        String sql = "SELECT COUNT(*) FROM bookings WHERE showtime_id = ? AND seat_number = ?";

        Connection conn = DBConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, showtimeId);
        stmt.setInt(2, seatNumber);

        ResultSet rs = stmt.executeQuery();

        int count = 0;

        if (rs.next()) {
            count = rs.getInt(1);
        }

        rs.close();
        stmt.close();
        conn.close();

        return count > 0;
    }
}