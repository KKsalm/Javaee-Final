package Database;

import Model.Store;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public static void createStore(Store store) throws SQLException {

        statement.executeUpdate("INSERT INTO store(address, dayTurnover, monthTurnover)\n"
                + " VALUE ( '" + store.getAddress() + "', '" + store.getDayTurnover() + "', '" + store.getMonthTurnover() + "' );");

    }

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

    @Override
    public void add(Store store) throws SQLException, IllegalAccessException {
        super.add(store);
    }

    @Override
    public void delete(int id) throws SQLException {
        super.delete(id);
    }

    @Override
    public void update(Store store) throws SQLException, IllegalAccessException {
        super.update(store);
    }
}
