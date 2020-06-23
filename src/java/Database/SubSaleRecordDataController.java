package Database;

import Model.SubSaleRecord;
import javax.naming.NamingException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SubSaleRecordDataController extends DatabaseOperation<SubSaleRecord> {

    private Statement statement = null;

    public SubSaleRecordDataController() throws NamingException, SQLException {
        super();
        statement = super.getConnection().createStatement();
    }

    @Override
    public SubSaleRecord queryByID(int id) throws SQLException, IllegalAccessException, InstantiationException {
        return super.queryByID(id);
    }

    @Override
    public List<SubSaleRecord> queryAll() throws IllegalAccessException, InstantiationException, SQLException {
        return super.queryAll();
    }

    @Override
    public void add(SubSaleRecord object) throws SQLException, IllegalAccessException {
        super.add(object);
    }

    @Override
    public void delete(int id) throws SQLException {
        super.delete(id);
    }

    @Override
    public void update(SubSaleRecord object) throws SQLException, IllegalAccessException {
        super.update(object);
    }
}
