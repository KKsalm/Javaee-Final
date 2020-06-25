package Database;

import Model.MonthlySaleAmount;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.naming.NamingException;

public class MonthlySaleAmountDataController extends DatabaseOperation<MonthlySaleAmount> {

    private static Statement statement = null;

    public MonthlySaleAmountDataController() throws NamingException, SQLException {
        super();
        statement = super.getConnection().createStatement();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static void createSaleAmount(int storeID) throws SQLException {

        statement.executeUpdate("INSERT INTO monthlysaleamount(yearMonth,amount,storeID)"
                + "SELECT date_format(date,'%Y-%m')yearMonth, sum(totalPrice)amount, storeID "
                + "FROM salerecord WHERE storeID = '" + storeID
                + "'group by year(date), month(date);");

    }

    @Override
    public MonthlySaleAmount queryByID(int id) throws SQLException, IllegalAccessException, InstantiationException {
        return super.queryByID(id);
    }

    @Override
    public List<MonthlySaleAmount> queryAll() throws IllegalAccessException, InstantiationException, SQLException {
        return super.queryAll();
    }

    @Override
    public void delete(int id) throws SQLException {
        super.delete(id);
    }
}
