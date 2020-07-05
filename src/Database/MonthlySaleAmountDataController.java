package Database;

import Model.MonthlySaleAmount;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.naming.NamingException;

public class MonthlySaleAmountDataController extends DatabaseOperation<MonthlySaleAmount> {

    private static Statement statement = null;
    private static Class modelClass = null;

    public MonthlySaleAmountDataController() throws NamingException, SQLException {
        super();
        statement = super.getConnection().createStatement();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static void createSaleAmount(int storeID) throws SQLException {

        statement.executeUpdate("INSERT INTO monthlySaleAmount(yearMonth,amount,storeID)"
                + "SELECT date_format(date,'%Y-%m')yearMonth, sum(totalPrice)amount, storeID "
                + "FROM saleRecord WHERE storeID = '" + storeID
                + "'group by year(date), month(date);");

    }

    public MonthlySaleAmount query(int id) throws SQLException, IllegalAccessException, InstantiationException, ParseException {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
        String today = sf.format(date);
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        modelClass = (Class<MonthlySaleAmount>) parameterizedType.getActualTypeArguments()[0];
        MonthlySaleAmount object = (MonthlySaleAmount) modelClass.newInstance();
        Field[] fields = modelClass.getDeclaredFields();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM monthlySaleAmount WHERE storeID = '" + id + "'AND yearMonth = \"" + today + "\"");
        if (resultSet.next()) {
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(object, resultSet.getObject(field.getName()));
            }

        }
        return object;
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
