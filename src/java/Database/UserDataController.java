package Database;

import Model.User;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDataController extends DatabaseOperation<User> {
    private static Connection connection = null;
    private static Statement statement = null;

    public UserDataController() throws SQLException, ClassNotFoundException, NamingException {
        super();
        initConnection();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }

    public void initConnection() throws SQLException, ClassNotFoundException, NamingException {
        DataSourceController dataSourceController = new DataSourceController();
        connection = dataSourceController.getConnection();
        statement = connection.createStatement();
    }

    public ResultSet getAllUsers() throws SQLException {
        return statement.executeQuery("SELECT workNumber, name, position, monthWorkTime, monthSalary FROM user");
    }

    public ResultSet getSingleUser(User user) throws SQLException {
        return statement.executeQuery("SELECT workNumber, name, position, monthWorkTime, monthSalary FROM user " +
                "WHERE username = '" + user.getUsername() + "';");
    }

    public String getPassword(User user) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT password FROM user WHERE username = 'test';");
        assert resultSet != null;
        resultSet.next();
        System.out.println(resultSet.getString("password"));
        return resultSet.getString("password");
    }

    public User queryByID(int id) throws SQLException, IllegalAccessException, InstantiationException {
        return super.queryByID(id);
    }

    public List<User> queryAll() throws IllegalAccessException, InstantiationException, SQLException {
        return super.queryAll();
    }

    public void registerUser(User user) throws SQLException {
        statement.executeUpdate("INSERT INTO user( username, password )\n" +
                " VALUE ( '" + user.getUsername() + "', '" + user.getPassword() + "' );");
    }


}
