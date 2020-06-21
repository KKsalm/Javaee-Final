package main.java.Database;

import main.java.Model.Material;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class MaterialDataController extends DatabaseOperation<Material>{
    private static Statement statement = null;

    public MaterialDataController() throws NamingException, SQLException {
        super();
        statement = super.getConnection().createStatement();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public Material queryByID(int id) throws SQLException, IllegalAccessException, InstantiationException {
        return super.queryByID(id);
    }

    @Override
    public List<Material> queryAll() throws IllegalAccessException, InstantiationException, SQLException {
        return super.queryAll();
    }

    @Override
    public void add(Material object) throws SQLException, IllegalAccessException {
        super.add(object);
    }

    @Override
    public void delete(int id) throws SQLException {
        super.delete(id);
    }

    @Override
    public void update(Material object) throws SQLException, IllegalAccessException {
        super.update(object);
    }
}
