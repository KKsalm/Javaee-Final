package Database;

import Model.SaleRecord;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class SaleRecordDataController extends DatabaseOperation<SaleRecord> {

    private static Statement statement = null;

    public SaleRecordDataController() throws SQLException, NamingException {
        super();
        statement = super.getConnection().createStatement();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

//    public static void createSaleRecord(SaleRecord saleRecord) throws SQLException {
//
//        statement.executeUpdate("INSERT INTO saleRecord(totalPrice, date) "
//                + " VALUE ( '" + saleRecord.getTotalPrice() + "', '" + saleRecord.getDate() + "' );");
//
//    }
//
//    public static ResultSet getSaleRecord() throws SQLException {
//        assert statement != null;
//        ResultSet resultSet = statement.executeQuery("SELECT saleRecordID, totalPrice, date FROM saleRecord");
//        return resultSet;
//
//    }

    @Override
    public SaleRecord queryByID(int id) throws SQLException, IllegalAccessException, InstantiationException {
        return super.queryByID(id);
    }

    public List<SaleRecord> queryByStoreID(int storeID) throws SQLException, IllegalAccessException, InstantiationException {
        ResultSet resultSet = statement.executeQuery("SELECT  * FROM saleRecord WHERE storeID = " + storeID + ";");
        Field[] fields = SaleRecord.class.getDeclaredFields();
        List<SaleRecord> saleRecords = new ArrayList<>();
        while (resultSet.next()) {
            SaleRecord saleRecord = new SaleRecord();
            for (Field field: fields) {
                field.setAccessible(true);
                field.set(saleRecord, resultSet.getObject(field.getName()));
            }
            saleRecords.add(saleRecord);
        }

        return saleRecords;
    }

    @Override
    public List<SaleRecord> queryAll() throws IllegalAccessException, InstantiationException, SQLException {
        return super.queryAll();
    }

    public static void modifySaleRecord(SaleRecord saleRecord) throws SQLException {

        statement.executeUpdate("UPDATE saleRecord SET totalPrice='" + saleRecord.getTotalPrice()
                + "'date = '" + saleRecord.getDate() + "'WHERE saleRecordID = '" + saleRecord.getSaleRecordID() + "';");

    }

    public static void deleteSaleRecord(SaleRecord saleRecord) throws SQLException {

        statement.executeUpdate("DELETE FROM saleRecord " + "WHERE saleRecordID = '" + saleRecord.getSaleRecordID() + "';");
    }

    @Override
    public void add(SaleRecord record) throws SQLException, IllegalAccessException {
        super.add(record);
    }

    @Override
    public void delete(int id) throws SQLException {
        super.delete(id);
    }

    @Override
    public void update(SaleRecord record) throws SQLException, IllegalAccessException {
        super.update(record);
    }

}
