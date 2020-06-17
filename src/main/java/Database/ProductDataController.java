package main.java.Database;

import main.java.Model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductDataController {
    private static Connection connection = null;
    private static Statement statement = null;

    public ProductDataController() throws SQLException, ClassNotFoundException {
        Class.forName(DatabaseController.getJdbcDRIVER());
        connection = DatabaseController.getConnection();
        statement = connection.createStatement();
    }

    public static void closeConnection() throws SQLException {
        connection.close();
    }

    public static ResultSet getProducts() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT productID, productName, productPrice FROM product;");
        return resultSet;
    }

    public static void createProduct(Product product) throws SQLException {
        statement.executeUpdate("INSERT INTO product(productName, productPrice) " +
                "VALUE ( '" + product.getProductName() + "', '" + product.getProductPrice() + "');");
    }

    public static void deleteProduct(Product product) throws SQLException {
        statement.executeUpdate("DELETE FROM product WHERE productID = " + product.getProductID() + ";");
    }
}
