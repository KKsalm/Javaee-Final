package Database;

import Model.PurchaseRecord;

import javax.naming.NamingException;
import java.io.File;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseDataController extends DatabaseOperation<PurchaseRecord> {
    private Statement statement = null;

    public PurchaseDataController() throws NamingException, SQLException {
        super();
        statement = super.getConnection().createStatement();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public PurchaseRecord queryByID(int id) throws SQLException, IllegalAccessException, InstantiationException {
        return super.queryByID(id);
    }

    public List<PurchaseRecord> queryByDate(Date date) throws SQLException, IllegalAccessException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM purchaseRecord WHERE date = '" + date + "';");
        List<PurchaseRecord> purchaseRecords = new ArrayList<>();
        Field[] columns = PurchaseRecord.class.getDeclaredFields();
        while (resultSet.next()) {
            PurchaseRecord purchaseRecord = new PurchaseRecord();
            for (Field field : columns) {
                field.setAccessible(true);
                field.set(purchaseRecord, resultSet.getObject(field.getName()));
            }
            purchaseRecords.add(purchaseRecord);
        }
        return purchaseRecords;
    }

    @Override
    public List<PurchaseRecord> queryAll() throws IllegalAccessException, InstantiationException, SQLException {
        return super.queryAll();
    }

    @Override
    public void add(PurchaseRecord object) throws SQLException, IllegalAccessException {
        super.add(object);
    }

    @Override
    public void delete(int id) throws SQLException {
        super.delete(id);
    }

    @Override
    public void update(PurchaseRecord object) throws SQLException, IllegalAccessException {
        super.update(object);
    }
}
