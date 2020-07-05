package Database;

import Model.Store;
import java.lang.reflect.Field;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class StoreDataController extends DatabaseOperation<Store> {

    private static Statement statement = null;

    public StoreDataController() throws SQLException, NamingException {
        super();
        statement = super.getConnection().createStatement();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static ResultSet getStore() throws SQLException {
        assert statement != null;
        ResultSet resultSet = statement.executeQuery("SELECT * FROM store;");
        return resultSet;
    }

//    public static void createStore(Store store) throws SQLException {
//
//        statement.executeUpdate("INSERT INTO store(address, dayTurnover, monthTurnover)\n"
//                + " VALUE ( '" + store.getAddress() + "' );");
//
//    }

    public static void deleteStore(Store store) throws SQLException {
        statement.executeUpdate("DELETE FROM store WHERE storeID = '" + store.getStoreID() + "';");
    }

    @Override
    public Store queryByID(int id) throws SQLException, IllegalAccessException, InstantiationException {
        return super.queryByID(id);
    }

    @Override
    public List<Store> queryAll() throws IllegalAccessException, InstantiationException, SQLException {
        return super.queryAll();
    }

    public List<Store> queryByIDStatusFalse() throws SQLException, IllegalArgumentException, IllegalAccessException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM store WHERE status = 0;");
        Field[] columns = Store.class.getDeclaredFields();
        List<Store> stores = new ArrayList<>();
        while (resultSet.next()) {
            Store store = new Store();
            for (Field column : columns) {
                column.setAccessible(true);
                column.set(store, resultSet.getObject(column.getName()));
            }
            stores.add(store);
        }
        return stores;
    }

    @Override
    public void add(Store store) throws SQLException, IllegalAccessException {
        super.add(store);
    }

    public void delete(int id) throws SQLException {
        statement.executeUpdate("UPDATE user SET storeID = null WHERE storeID = " + id + ";");
        statement.executeUpdate("UPDATE store SET status = true WHERE storeID = " + id + ";");
        statement.executeUpdate("DELETE FROM saleRecord WHERE storeID = " + id + ";");
        statement.executeUpdate("DELETE FROM dailySaleAmount WHERE storeID = " + id + ";");
        statement.executeUpdate("DELETE FROM monthlySaleAmount WHERE storeID = " + id + ";");
    }

    @Override
    public void update(Store store) throws SQLException, IllegalAccessException {
        super.update(store);
    }
}
