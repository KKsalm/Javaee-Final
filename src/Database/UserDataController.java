package Database;

import Model.User;

import javax.naming.NamingException;
import java.lang.reflect.Field;
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
        return resultSet.getString("password");
    }

    @Override
    public User queryByID(int id) throws SQLException, IllegalAccessException, InstantiationException {
        return super.queryByID(id);
    }

    public User queryByUsername(String username) throws SQLException, IllegalAccessException, InstantiationException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE username = '" + username + "';");
        Field[] fields = User.class.getDeclaredFields();
        User user = User.class.newInstance();
        resultSet.next();
        for (Field field : fields) {
            field.setAccessible(true);
            field.set(user, resultSet.getObject(field.getName()));
        }
        return user;
    }

    @Override
    public List<User> queryAll() throws IllegalAccessException, InstantiationException, SQLException {
        return super.queryAll();
    }

    public void registerUser(User user) throws SQLException {
        statement.executeUpdate("INSERT INTO user( username, password )\n"
                + " VALUE ( '" + user.getUsername() + "', '" + user.getPassword() + "' );");
    }

    @Override
    public void delete(int id) throws SQLException {
        super.delete(id);
    }

    @Override
    public void update(User object) throws SQLException, IllegalAccessException {
        super.update(object);
    }

    public static void updateInfo(int id, String password) throws SQLException {

        statement.executeUpdate("UPDATE user SET password = '" + password + "'WHERE userID = '" + id + "';");

    }

    public static void updateInfos(int id, String position, int id2, float num1, float num2) throws SQLException {

    }
}
