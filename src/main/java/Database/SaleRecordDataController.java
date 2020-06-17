package main.java.Database;


import main.java.Model.SaleRecord;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SaleRecordDataController {

    private static Connection connection = null;
    private static Statement statement = null;

    public SaleRecordDataController() throws SQLException, ClassNotFoundException {
        Class.forName(DatabaseController.getJdbcDRIVER());
        connection = DatabaseController.getConnection();
        statement = connection.createStatement();
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    public static void createSaleRecord(SaleRecord saleRecord) throws SQLException {

        statement.executeUpdate("INSERT INTO saleRecord(totalPrice, date)\n"
                + " VALUE ( '" + saleRecord.getTotalPrice() + saleRecord.getDate() + "' );");

    }

    public static ResultSet getSaleRecord() throws SQLException {

        ResultSet resultSet = statement.executeQuery("SELECT totalPrice, date FROM saleRecord");
        return resultSet;

    }

    public static void modifySaleRecord(SaleRecord saleRecord) throws SQLException {

        statement.executeUpdate("UPDATE saleRecord SET totalPrice=" + saleRecord.getTotalPrice()
                + "date = " + saleRecord.getDate() + "WHERE saleRecordID = '" + saleRecord.getSaleRecordID() + "';");

    }

    public static void deleteSaleRecord(SaleRecord saleRecord) throws SQLException {

        statement.executeUpdate("DELETE FROM saleRecord " + "WHERE saleRecordID = '" + saleRecord.getSaleRecordID() + "';");
    }
}
