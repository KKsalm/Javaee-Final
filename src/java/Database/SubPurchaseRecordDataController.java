package Database;

import Model.SubPurchaseRecord;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SubPurchaseRecordDataController extends DatabaseOperation<SubPurchaseRecord> {
    private Statement statement = null;

    public SubPurchaseRecordDataController() throws NamingException, SQLException {
        super();
        statement = super.getConnection().createStatement();
    }

    @Override
    public SubPurchaseRecord queryByID(int id) throws SQLException, IllegalAccessException, InstantiationException {
        return super.queryByID(id);
    }

    @Override
    public List<SubPurchaseRecord> queryAll() throws IllegalAccessException, InstantiationException, SQLException {
        return super.queryAll();
    }

    @Override
    public void add(SubPurchaseRecord object) throws SQLException, IllegalAccessException {
        super.add(object);
    }

    @Override
    public void delete(int id) throws SQLException {
        super.delete(id);
    }

    @Override
    public void update(SubPurchaseRecord object) throws SQLException, IllegalAccessException {
        super.update(object);
    }
}
