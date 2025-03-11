package me.i.springapi.api.service;

import me.i.springapi.api.model.User;

import java.sql.*;

public class DataBaseWorker {
    private final String url = "jdbc:postgresql://localhost:5433/db1";
    private final String username = "postgres";
    private final String password = "postgrespassword";

    public User selectQuery(String login) throws DataBaseException, SQLException {
        String query = "select * from table1 t1 join table2 t2 using (login) where login='" + login + "';";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        User user = new User();
        try {
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            resultSet.next();
            if (resultSet.getString("login") == null)
                throw new DataBaseException("There is no such user in the database.");
            user = new User(
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("email"),
                    resultSet.getTimestamp("date"));
        } finally {
            try {
                if (connection != null)
                    connection.close();
                if (statement != null)
                    statement.close();
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    public int insertQuery(User user) throws SQLException {
        String query = "insert into table1(login, password, date) values(?,?,?);\ninsert into table2(login, email) values(?,?)";
        int rowsAdded = 0;
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setTimestamp(3, user.getDate());
            statement.setString(4, user.getLogin());
            statement.setString(5, user.getEmail());
            rowsAdded = statement.executeUpdate();
        }
        return rowsAdded;
    }
}

