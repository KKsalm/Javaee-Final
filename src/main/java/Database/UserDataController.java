package main.java.Database;

import main.java.Model.User;

import javax.naming.NamingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDataController extends DatabaseOperation<User> {
    private static Statement statement = null;

    public UserDataController() throws SQLException, ClassNotFoundException, NamingException {
        super();
        statement = super.getConnection().createStatement();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    public String getPassword(User user) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT password FROM user WHERE username = 'test';");
        assert resultSet != null;
        resultSet.next();
        System.out.println(resultSet.getString("password"));
        return resultSet.getString("password");
    }

    @Override
    public User queryByID(int id) throws SQLException, IllegalAccessException, InstantiationException {
        return super.queryByID(id);
    }

    @Override
    public List<User> queryAll() throws IllegalAccessException, InstantiationException, SQLException {
        return super.queryAll();
    }

    public void registerUser(User user) throws SQLException {
        statement.executeUpdate("INSERT INTO user( username, password )\n" +
                " VALUE ( '" + user.getUsername() + "', '" + user.getPassword() + "' );");
    }

    @Override
    public void delete(int id) throws SQLException {
        super.delete(id);
    }

    @Override
    public void update(User object) throws SQLException, IllegalAccessException {
        super.update(object);
    }
}
