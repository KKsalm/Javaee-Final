package Database;

import Model.DailySaleAmount;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.naming.NamingException;

public class DailySaleAmountDataController extends DatabaseOperation<DailySaleAmount> {

    private static Statement statement = null;

    public DailySaleAmountDataController() throws NamingException, SQLException {
        super();
        statement = super.getConnection().createStatement();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static void createSaleAmount(int storeID) throws SQLException {

        statement.executeUpdate("INSERT INTO dailysaleamount(date,amount,storeID)"
                + "SELECT date, sum(totalPrice)amount, storeID "
                + "FROM salerecord WHERE storeID = '" + storeID
                + "'group by year(date), month(date), day(date);");

    }

    @Override
    public DailySaleAmount queryByID(int id) throws SQLException, IllegalAccessException, InstantiationException {
        return super.queryByID(id);
    }

    @Override
    public List<DailySaleAmount> queryAll() throws IllegalAccessException, InstantiationException, SQLException {
        return super.queryAll();
    }

    @Override
    public void delete(int id) throws SQLException {
        super.delete(id);
    }
}
