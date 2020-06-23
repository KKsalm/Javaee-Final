package Test;

import Database.DatabaseController;
import Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.lang.reflect.Field;
import java.sql.*;

public class DatabaseControllerTest {
    private static final String jdbcDRIVER = "com.mysql.jdbc.Driver";
    private static final String dbURLParams = "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String dbURL = "jdbc:mysql://localhost:3306";
    private static final String dbName = "yhtSaleSystem";

    static final String dbUsername = "root";
    static final String dbPassword = "root";

    private static int databaseInitStatue = 0;
    private static Logger logger = LogManager.getLogger(DatabaseController.class);
    ;
    // Configurator.setLevel(databaseConnection.class.getName(), Level.DEBUG);

    private static Connection connection = null;
    private static Statement statement = null;

//    DatabaseController() {
//        if (databaseInitStatue == 0) {
//            initDatabase();
//        }
//    }

    public static void main(String[] args) {
//        try {
//            initDatabase();
//        } catch (SQLException sqlException) {
//            if (sqlException.getErrorCode() == 1007) {
//                // Database already exist
//                logger.warn("Database " + dbName + "Already Exist");
//            } else {
//                logger.fatal(sqlException.getMessage());
//            }
//        } catch (ClassNotFoundException classNotFoundException) {
//            // if "com.mysql.cj.jdbc.Driver" does not exist
//            logger.error(classNotFoundException.getMessage());
//        }

        try {
            User user = new User();
            user.setUsername("test");
            user.setPassword("test");

            connection = DriverManager.getConnection(dbURL + "/" + dbName + dbURLParams, dbUsername, dbPassword);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT password FROM user WHERE username = 'test';");



            while (resultSet.next()) {
                System.out.println(resultSet.getString("password"));
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }


    public static void initDatabase() throws SQLException, ClassNotFoundException {
        Class.forName(jdbcDRIVER);

        logger.info("Connecting to Database ...");
        connection = DriverManager.getConnection(dbURL + dbURLParams, dbUsername, dbPassword);

        logger.info("Create Statement Object ...");
        statement = connection.createStatement();

        if (connection != null && statement != null) {
            createDatabase(connection, statement);
        }

    }

    public static void createDatabase(Connection connection, Statement statement) throws SQLException {
        String sqlQuery;

        logger.info("Create Database " + dbName + " ...");
        statement.executeUpdate("CREATE DATABASE " + dbName + " COLLATE 'utf8_general_ci';");
        logger.info("Database Create Successfully !");

        logger.info("Use Database " + dbName);
        connection.close();
        connection = DriverManager.getConnection(dbURL + "/" + dbName + dbURLParams, dbUsername, dbPassword);
        statement = connection.createStatement();

        logger.info("Create Store Table ...");
        sqlQuery = "CREATE TABLE store\n" +
                "(\n" +
                "    storeID       INT          NOT NULL AUTO_INCREMENT,\n" +
                "    address       VARCHAR(100) NOT NULL,\n" +
                "    dayTurnover   FLOAT        NULL,\n" +
                "    monthTurnover FLOAT        NULL,\n" +
                "    CONSTRAINT STORE_PK PRIMARY KEY (storeID)\n" +
                ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Product Table Successfully !");

        logger.info("Create User Table ...");
        sqlQuery = "CREATE TABLE user\n" +
                "(\n" +
                "    userID        INT          NOT NULL AUTO_INCREMENT,\n" +
                "    workNumber    INT          NULL,\n" +
                "    name          VARCHAR(30)  NULL,\n" +
                "    position      VARCHAR(30)  NULL,\n" +
                "    CHECK (position IN ('boss', 'manager', 'staff', NULL)),\n" +
                "    storeID       INT          NULL,\n" +
                "    monthWorkTime FLOAT        NULL DEFAULT 0,\n" +
                "    monthSalary   FLOAT        NULL DEFAULT 0,\n" +
                "    username      VARCHAR(100) NOT NULL UNIQUE,\n" +
                "    password      VARCHAR(100) NOT NULL,\n" +
                "    CONSTRAINT USER_PK PRIMARY KEY (userid),\n" +
                "    CONSTRAINT STORE_FK FOREIGN KEY (storeID) REFERENCES store (storeID)\n" +
                ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create User Table Successfully !");


        logger.info("Create Product Table ...");
        sqlQuery = "CREATE TABLE product\n" +
                "(\n" +
                "    productID        INT          NOT NULL AUTO_INCREMENT,\n" +
                "    productSerialNum VARCHAR(100) NOT NULL,\n" +
                "    productName      VARCHAR(50)  NOT NULL,\n" +
                "    productPrice     FLOAT        NOT NULL,\n" +
                "    CONSTRAINT PRODUCT_PK PRIMARY KEY (productID)\n" +
                ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Product Table Successfully !");

        logger.info("Create Material Table ...");
        sqlQuery = "CREATE TABLE material\n" +
                "(\n" +
                "    materialID   INT         NOT NULL AUTO_INCREMENT,\n" +
                "    materialName VARCHAR(30) NOT NULL,\n" +
                "    CONSTRAINT MATERIAL_PK PRIMARY KEY (materialID)\n" +
                ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Material Table Successfully !");

        logger.info("Create Purchase Record Table ...");
        sqlQuery = "CREATE TABLE purchaseRecord\n" +
                "(\n" +
                "    purchaseRecordID INT         NOT NULL AUTO_INCREMENT,\n" +
                "    totalPrice       FLOAT       NOT NULL,\n" +
                "    date             VARCHAR(30) NOT NULL,\n" +
                "    CONSTRAINT PURCHASERECORD_PK PRIMARY KEY (purchaseRecordID)\n" +
                ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Purchase Record Table Successfully !");

        logger.info("Create Sub Purchase Record Record Table ...");
        sqlQuery = "CREATE TABLE subPurchaseRecord\n" +
                "(\n" +
                "    recordID         INT   NOT NULL AUTO_INCREMENT,\n" +
                "    purchaseRecordID INT   NOT NULL,\n" +
                "    materialID       INT   NOT NULL,\n" +
                "    number           INT   NOT NULL,\n" +
                "    price            FLOAT Not Null,\n" +
                "    CONSTRAINT SUBPURCHASERECORD_PK PRIMARY KEY (recordID),\n" +
                "    CONSTRAINT PURCHASERECORD_FK FOREIGN KEY (purchaseRecordID) REFERENCES purchaseRecord (purchaseRecordID),\n" +
                "    CONSTRAINT MATERIAL_FK FOREIGN KEY (materialID) REFERENCES material (materialID)\n" +
                ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Sub Purchase Record Table Successfully !");

        logger.info("Create Sale Record Table ...");
        sqlQuery = "CREATE TABLE saleRecord\n" +
                "(\n" +
                "    saleRecordID INT      NOT NULL AUTO_INCREMENT,\n" +
                "    totalPrice   FLOAT    NOT NULL,\n" +
                "    date         DATETIME NOT NULL,\n" +
                "    CONSTRAINT SALERECORD_PK PRIMARY KEY (saleRecordID)\n" +
                ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Sale Record Table Successfully !");

        logger.info("Create Sub Sale Record Table ...");
        sqlQuery = "CREATE TABLE subSaleRecord\n" +
                "(\n" +
                "    subSaleRecordID INT   NOT NULL AUTO_INCREMENT,\n" +
                "    saleRecordID    INT   NOT NULL,\n" +
                "    productID       INT   NOT NULL,\n" +
                "    productNum      INT   NOT NULL,\n" +
                "    price           FLOAT NOT NULL,\n" +
                "    CONSTRAINT SUBSALERECPRD_PK PRIMARY KEY (subSaleRecordID),\n" +
                "    CONSTRAINT SALERECORD_FK FOREIGN KEY (saleRecordID) REFERENCES saleRecord (saleRecordID),\n" +
                "    CONSTRAINT PRODUCT_FK FOREIGN KEY (productID) REFERENCES product (productID)\n" +
                ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Sub Sale Record Table Successfully !");

        logger.info("Create Daily Sale Amount Table ...");
        sqlQuery = "CREATE TABLE dailySaleAmount\n" +
                "(\n" +
                "    dailySaleAmountID INT         NOT NULL AUTO_INCREMENT,\n" +
                "    date              DATE NOT NULL,\n" +
                "    amount            FLOAT       NOT NULL,\n" +
                "    CONSTRAINT DAILYSALEAMOUNT_PK PRIMARY KEY (dailySaleAmountID)\n" +
                ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Daily Sale Amount Successfully !");

        logger.info("Create Monthly Sale Amount Table ...");
        sqlQuery = "CREATE TABLE monthlySaleAmount\n" +
                "(\n" +
                "    monthlySaleAmountID INT         NOT NULL AUTO_INCREMENT,\n" +
                "    yearMonth           VARCHAR(30) NOT NULL,\n" +
                "    amount              FLOAT       NOT NULL,\n" +
                "    CONSTRAINT MONTHLYSALEAMOUNT_PK PRIMARY KEY (monthlySaleAmountID)\n" +
                ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Monthly Sale Amount Successfully !");

        connection.close();
        databaseInitStatue = 1;
    }

    public static int registerUser(User user) throws SQLException {
        connection = DriverManager.getConnection(dbURL + "/" + dbName + dbURLParams, dbUsername, dbPassword);
        statement = connection.createStatement();

        int result = statement.executeUpdate("INSERT INTO user(name, username, password)\n" +
                " VALUE ( '" + user.getName() + "', '" + user.getUsername() + "', '" + user.getPassword() + "' );");

        return 0;
    }

    public static String getPassword(String username) throws SQLException {
        connection = DriverManager.getConnection(dbURL + "/" + dbName + dbURLParams, dbUsername, dbPassword);
        statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT password FROM user\n" +
                "WHERE username = " + username + ";");

        connection.close();
        resultSet.next();
        return resultSet.getString("password");
    }


}
