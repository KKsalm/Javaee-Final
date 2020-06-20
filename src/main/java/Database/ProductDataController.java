package main.java.Database;

import main.java.Model.Product;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ProductDataController extends DatabaseOperation<Product>{
    private static Connection connection = null;
    private static Statement statement = null;

    public ProductDataController() throws SQLException, NamingException {
        super();
        statement = super.getConnection().createStatement();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
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

    @Override
    public Product queryByID(int id) throws SQLException, IllegalAccessException, InstantiationException {
        return super.queryByID(id);
    }

    @Override
    public List<Product> queryAll() throws IllegalAccessException, InstantiationException, SQLException {
        return super.queryAll();
    }

    @Override
    public void add(Product object) throws SQLException, IllegalAccessException {
        super.add(object);
    }

    @Override
    public void delete(int id) throws SQLException {
        super.delete(id);
    }

    @Override
    public void update(Product object) throws SQLException, IllegalAccessException {
        super.update(object);
    }
}
