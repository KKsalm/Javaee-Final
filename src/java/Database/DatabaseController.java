package Database;

import java.sql.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class DatabaseController {

    private static final String jdbcDRIVER = "com.mysql.jdbc.Driver";
    private static final String dbURLParams = "?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
    private static final String dbURL = "jdbc:mysql://localhost:3306";
    private static final String dbName = "yhtSaleSystem";

    private static final String dbUsername = "root";
    private static final String dbPassword = "root";

    private static final Logger logger = LogManager.getLogger(DatabaseController.class);

    ;
    // Configurator.setLevel(databaseConnection.class.getName(), Level.DEBUG);

    public DatabaseController() {

    }

    public static void initDatabase() throws SQLException, ClassNotFoundException {

        Class.forName(jdbcDRIVER);

        logger.info("Connecting to Database ...");
        Connection connection = DriverManager.getConnection(dbURL + dbURLParams, dbUsername, dbPassword);

        logger.info("Create Statement Object ...");
        Statement statement = connection.createStatement();

        if (statement != null) {
            createDatabase(connection, statement);
        }

//        try {
//            Class.forName(jdbcDRIVER);
//
//            logger.info("Connecting to Database ...");
//            Connection connection = DriverManager.getConnection(dbURL + dbURLParams, dbUsername, dbPassword);
//
//            logger.info("Create Statement Object ...");
//            Statement statement = connection.createStatement();
//
//            if (connection != null && statement != null) {
//                createDatabase(connection, statement);
//            }
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
        sqlQuery = "CREATE TABLE store\n"
                + "(\n"
                + "    storeID       INT          NOT NULL AUTO_INCREMENT,\n"
                + "    address       VARCHAR(100) NOT NULL,\n"
                + "    dayTurnover   FLOAT        DEFAULT 0,\n"
                + "    monthTurnover FLOAT        DEFAULT 0,\n"
                + "    CONSTRAINT STORE_PK PRIMARY KEY (storeID)\n"
                + ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Product Table Successfully !");

        logger.info("Create User Table ...");
        sqlQuery = "CREATE TABLE user\n"
                + "(\n"
                + "    userID        INT          NOT NULL AUTO_INCREMENT,\n"
                + "    workNumber    INT          NULL,\n"
                + "    name          VARCHAR(30)  NULL,\n"
                + "    position      VARCHAR(30)  NULL,\n"
                + "    CHECK (position IN ('boss', 'manager', 'staff', NULL)),\n"
                + "    storeID       INT          NULL,\n"
                + "    monthWorkTime FLOAT        NULL DEFAULT 0,\n"
                + "    monthSalary   FLOAT        NULL DEFAULT 0,\n"
                + "    username      VARCHAR(100) NOT NULL UNIQUE,\n"
                + "    password      VARCHAR(100) NOT NULL,\n"
                + "    CONSTRAINT USER_PK PRIMARY KEY (userid),\n"
                + "    CONSTRAINT STORE_FK FOREIGN KEY (storeID) REFERENCES store (storeID)\n"
                + ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create User Table Successfully !");

        logger.info("Create Product Table ...");
        sqlQuery = "CREATE TABLE product\n"
                + "(\n"
                + "    productID        INT          NOT NULL AUTO_INCREMENT,\n"
                + "    productSerialNum VARCHAR(100) NOT NULL,\n"
                + "    productName      VARCHAR(50)  NOT NULL,\n"
                + "    productPrice     FLOAT        NOT NULL,\n"
                + "    CONSTRAINT PRODUCT_PK PRIMARY KEY (productID)\n"
                + ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Product Table Successfully !");

        logger.info("Create Material Table ...");
        sqlQuery = "CREATE TABLE material\n"
                + "(\n"
                + "    materialID   INT         NOT NULL AUTO_INCREMENT,\n"
                + "    materialName VARCHAR(30) NOT NULL,\n"
                + "    CONSTRAINT MATERIAL_PK PRIMARY KEY (materialID)\n"
                + ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Material Table Successfully !");

        logger.info("Create Purchase Record Table ...");
        sqlQuery = "CREATE TABLE purchaseRecord\n"
                + "(\n"
                + "    purchaseRecordID INT         NOT NULL AUTO_INCREMENT,\n"
                + "    totalPrice       FLOAT       NOT NULL,\n"
                + "    date             VARCHAR(30) NOT NULL,\n"
                + "    CONSTRAINT PURCHASERECORD_PK PRIMARY KEY (purchaseRecordID)\n"
                + ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Purchase Record Table Successfully !");

        logger.info("Create Sub Purchase Record Record Table ...");
        sqlQuery = "CREATE TABLE subPurchaseRecord\n"
                + "(\n"
                + "    recordID         INT   NOT NULL AUTO_INCREMENT,\n"
                + "    purchaseRecordID INT   NOT NULL,\n"
                + "    materialID       INT   NOT NULL,\n"
                + "    number           INT   NOT NULL,\n"
                + "    price            FLOAT NOT NUll,\n"
                + "    CONSTRAINT SUBPURCHASERECORD_PK PRIMARY KEY (recordID),\n"
                + "    CONSTRAINT PURCHASERECORD_FK FOREIGN KEY (purchaseRecordID) REFERENCES purchaseRecord (purchaseRecordID),\n"
                + "    CONSTRAINT MATERIAL_FK FOREIGN KEY (materialID) REFERENCES material (materialID)\n"
                + ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Sub Purchase Record Table Successfully !");

        logger.info("Create Sale Record Table ...");
        sqlQuery = "CREATE TABLE saleRecord\n"
                + "(\n"
                + "    saleRecordID INT      NOT NULL AUTO_INCREMENT,\n"
                + "    totalPrice   FLOAT    NOT NULL,\n"
                + "    date         DATETIME NOT NULL,\n"
                + "    storeID      INT      NOT NULL,\n"
                + "    CONSTRAINT SALERECORD_PK PRIMARY KEY (saleRecordID),\n"
                + "    CONSTRAINT SALERECORD_FK FOREIGN KEY (storeID) REFERENCES store (storeID)\n"
                + ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Sale Record Table Successfully !");

        logger.info("Create Sub Sale Record Table ...");
        sqlQuery = "CREATE TABLE subSaleRecord\n"
                + "(\n"
                + "    subSaleRecordID INT   NOT NULL AUTO_INCREMENT,\n"
                + "    saleRecordID    INT   NOT NULL,\n"
                + "    productID       INT   NOT NULL,\n"
                + "    productNum      INT   NOT NULL,\n"
                + "    price           FLOAT NOT NULL,\n"
                + "    CONSTRAINT SUBSALERECPRD_PK PRIMARY KEY (subSaleRecordID),\n"
                + "    CONSTRAINT SUBSALERECPRD_FK FOREIGN KEY (saleRecordID) REFERENCES saleRecord (saleRecordID),\n"
                + "    CONSTRAINT PRODUCT_FK FOREIGN KEY (productID) REFERENCES product (productID)\n"
                + ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Sub Sale Record Table Successfully !");

        logger.info("Create Daily Sale Amount Table ...");
        sqlQuery = "CREATE TABLE dailySaleAmount\n"
                + "(\n"
                + "    dailySaleAmountID INT   NOT NULL AUTO_INCREMENT,\n"
                + "    date              DATE  NOT NULL,\n"
                + "    amount            FLOAT NOT NULL,\n"
                + "    storeID           INT   NOT NULL,\n"
                + "    CONSTRAINT DAILYSALEAMOUNT_PK PRIMARY KEY (dailySaleAmountID),\n"
                + "    CONSTRAINT DAILYSALEAMOUNT_FK FOREIGN KEY (storeID) REFERENCES store (storeID)\n"
                + ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Daily Sale Amount Successfully !");

        logger.info("Create Monthly Sale Amount Table ...");
        sqlQuery = "CREATE TABLE monthlySaleAmount\n"
                + "(\n"
                + "    monthlySaleAmountID INT         NOT NULL AUTO_INCREMENT,\n"
                + "    yearMonth           VARCHAR(30) NOT NULL,\n"
                + "    amount              FLOAT       NOT NULL,\n"
                + "    storeID             INT         NOT NULL,\n"
                + "    CONSTRAINT MONTHLYSALEAMOUNT_PK PRIMARY KEY (monthlySaleAmountID),\n"
                + "    CONSTRAINT MONTHLYSALEAMOUNT_FK FOREIGN KEY (storeID) REFERENCES store (storeID)\n"
                + ");";
        statement.executeUpdate(sqlQuery);
        logger.info("Create Monthly Sale Amount Successfully !");

        connection.close();
    }

    public static String getJdbcDRIVER() {
        return jdbcDRIVER;
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(jdbcDRIVER);
        return DriverManager.getConnection(dbURL + "/" + dbName + dbURLParams, dbUsername, dbPassword);
    }

}
