package Database;

import Model.DailySaleAmount;
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

public class DailySaleAmountDataController extends DatabaseOperation<DailySaleAmount> {

    private static Statement statement = null;
    private static Class modelClass = null;

    public DailySaleAmountDataController() throws NamingException, SQLException {
        super();
        statement = super.getConnection().createStatement();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public static void createSaleAmount(int storeID) throws SQLException {

        statement.executeUpdate("INSERT INTO dailySaleAmount(date,amount,storeID)"
                + "SELECT date, sum(totalPrice)amount, storeID "
                + "FROM saleRecord WHERE storeID = '" + storeID
                + "'group by year(date), month(date), day(date);");

    }

    public DailySaleAmount query(int id) throws SQLException, IllegalAccessException, InstantiationException, ParseException {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sf.format(date);
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        modelClass = (Class<DailySaleAmount>) parameterizedType.getActualTypeArguments()[0];
        DailySaleAmount object = (DailySaleAmount) modelClass.newInstance();
        Field[] fields = modelClass.getDeclaredFields();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM dailySaleAmount WHERE storeID = '" + id + "'AND date = \"" + today + "\"");
        if (resultSet.next()) {
            for (Field field : fields) {
                field.setAccessible(true);
                field.set(object, resultSet.getObject(field.getName()));
            }

        }
        return object;
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
