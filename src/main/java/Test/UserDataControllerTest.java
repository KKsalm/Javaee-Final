package main.java.Test;

import main.java.Database.DatabaseOperation;
import main.java.Model.User;

import javax.naming.NamingException;
import java.sql.SQLException;

public class UserDataControllerTest extends DatabaseOperation<User> {

    public static void main(String[] args) {
        //QueryByID(1);
    }

    public UserDataControllerTest() throws NamingException, SQLException {
        super();
    }

    @Override
    public User queryByID(int id) throws SQLException, IllegalAccessException, InstantiationException {
        return super.queryByID(id);
    }
}
