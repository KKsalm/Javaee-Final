package Database;

import Model.PurchaseRecord;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class PurchaseRecordDataController extends DatabaseOperation<PurchaseRecord> {
    private Statement statement = null;

    public PurchaseRecordDataController() throws NamingException, SQLException {
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
