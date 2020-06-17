package main.java.Database;

import main.java.Model.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDataController {
    private static Connection connection = null;
    private static Statement statement = null;

    public UserDataController() throws SQLException, ClassNotFoundException {
        Class.forName(DatabaseController.getJdbcDRIVER());
        connection = DatabaseController.getConnection();
        statement = connection.createStatement();
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    public static ResultSet getAllUsers() throws SQLException {
        return statement.executeQuery("SELECT workNumber, name, position, monthWorkTime, monthSalary FROM user");
    }

    public static void registerUser(User user) throws SQLException {
        statement.executeUpdate("INSERT INTO user( username, password )\n" +
                " VALUE ( '" + user.getUsername() + "', '" + user.getPassword() + "' );");
    }

    public static String getPassword(User user) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT password FROM user \n" +
                "WHERE username = '" + user.getUsername() + "';");

        if (resultSet != null) {
            resultSet.next();
        }

        return resultSet.getString("password");
    }

}
