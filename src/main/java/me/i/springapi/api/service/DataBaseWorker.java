package me.i.springapi.api.service;

import me.i.springapi.api.model.User;

import java.sql.*;

public class DataBaseWorker {
    private final String url = "jdbc:postgresql://10.0.2.15:5433/db1";
    private final String username = "postgres";
    private final String password = "postgrespassword";

    public User selectQuery(String login) {
        String query = "select * from table1 t1 join table2 t2 using (login) where login='" + login + "';";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        User user = new User();
        try {
            conn = DriverManager.getConnection(url, username, password);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            user = new User(
                    rs.getString("login"),
                    rs.getString("password"),
                    "",
                    rs.getString("email"),
                    rs.getTimestamp("date"));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null)
                    conn.close();
                if (stmt != null)
                    stmt.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public int insertQuery(User user) {
        String query = "insert into table1(login, password, date) values(?,?,?);\ninsert into table2(login, email) values(?,?)";
        int rowsAdded = 0;
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(query);) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.setTimestamp(3, user.getDate());
            stmt.setString(4, user.getLogin());
            stmt.setString(5, user.getEmail());
            rowsAdded = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAdded;
    }
}

