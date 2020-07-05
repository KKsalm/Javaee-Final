package Database;

import Model.Product;
import java.lang.reflect.Field;

import javax.naming.NamingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDataController extends DatabaseOperation<Product> {

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
        assert statement != null;
        ResultSet resultSet = statement.executeQuery("SELECT productID, productName, productPrice FROM product;");
        return resultSet;
    }

    public static void createProduct(Product product) throws SQLException {
        statement.executeUpdate("INSERT INTO product(productName, productPrice) "
                + "VALUE ( '" + product.getProductName() + "', '" + product.getProductPrice() + "');");
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
    public void update(Product object) throws SQLException, IllegalAccessException {
        super.update(object);
    }

    public List<Product> queryByIDStatusFalse() throws SQLException, IllegalArgumentException, IllegalAccessException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM product WHERE status = false;");
        Field[] columns = Product.class.getDeclaredFields();
        List<Product> products = new ArrayList<>();
        while (resultSet.next()) {
            Product product = new Product();
            for (Field column : columns) {
                column.setAccessible(true);
                column.set(product, resultSet.getObject(column.getName()));
            }
            products.add(product);
        }
        return products;
    }

    public void delete(int id) throws SQLException {
        statement.executeUpdate("UPDATE product SET status = true WHERE productID = " + id + ";");
    }
}
