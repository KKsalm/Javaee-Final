package main.java.Database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceController {
    private DataSource dataSource = null;
    private Connection connection = null;
    private Context context = null;

    public DataSourceController() throws NamingException, SQLException {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup("java:comp/env");
        dataSource = (DataSource) envCtx.lookup("jdbc/yhtSaleSystem");
        connection = dataSource.getConnection();
    }

    @Override
    protected void finalize() throws Throwable {
        connection.close();
    }

    public Connection getConnection() {
        return connection;
    }

    public Context getContext() {
        return context;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
