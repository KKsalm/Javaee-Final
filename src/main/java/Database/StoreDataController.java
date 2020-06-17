package main.java.Database;


import main.java.Model.Store;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StoreDataController {

    private static Connection connection = null;
    private static Statement statement = null;

    public StoreDataController() throws SQLException, ClassNotFoundException {
        Class.forName(DatabaseController.getJdbcDRIVER());
        connection = DatabaseController.getConnection();
        statement = connection.createStatement();
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    public static void createStore(Store store) throws SQLException {

        statement.executeUpdate("INSERT INTO store(address, dayTurnover, monthTurnover)\n"
                + " VALUE ( '" + store.getAddress() + store.getDayTurnover() + store.getMonthTurnover() + "' );");

    }

    public static void deleteStore(Store store) throws SQLException {
        statement.executeUpdate("DELETE FROM store WHERE storeID = '" + store.getStoreID() + "';");
    }

}
