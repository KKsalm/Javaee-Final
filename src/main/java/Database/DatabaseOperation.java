package main.java.Database;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperation<T> {
    private static Context context = null;
    private static DataSource dataSource = null;
    private static Connection connection = null;
    private static Statement statement = null;
    private static Class modelClass = null;

    public DatabaseOperation() throws NamingException, SQLException {
        DataSourceController dataSourceController = new DataSourceController();
        connection = dataSourceController.getConnection();
        statement = connection.createStatement();

        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        modelClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    @Override
    protected void finalize() throws Throwable {
        connection.close();
    }

    public Context getContext() {
        return context;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public Connection getConnection() {
        return connection;
    }

    public static String toLowercaseFirst(String string)
    {
        if(Character.isLowerCase(string.charAt(0)))
            return string;
        else
            return Character.toLowerCase(string.charAt(0)) + string.substring(1);
    }

    public T queryByID(int id) throws SQLException, IllegalAccessException, InstantiationException {
        T object = (T) modelClass.newInstance();
        Field[] fields = modelClass.getDeclaredFields();
        assert connection != null && statement != null;
        String tableName = toLowercaseFirst(modelClass.getSimpleName());
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE " + fields[0].getName() + " = '" + id + "';");
        resultSet.next();
        for (Field field: fields) {
            field.setAccessible(true);
            field.set(object, resultSet.getObject(field.getName()));
        }

        return object;
    }

    public List<T> queryAll() throws IllegalAccessException, InstantiationException, SQLException {
        List<T> list = new ArrayList<T>();
        Field[] fields = modelClass.getDeclaredFields();
        assert connection != null && statement != null;
        String tableName = toLowercaseFirst(modelClass.getSimpleName());
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + ";");
        while (resultSet.next()) {
            T object = (T) modelClass.newInstance();
            for (Field field: fields) {
                field.setAccessible(true);
                field.set(object, resultSet.getObject(field.getName()));
            }
            list.add(object);
        }
        return list;
    }

    public void delete(int id) throws SQLException {
        Field[] fields = modelClass.getDeclaredFields();
        fields[0].setAccessible(true);
        statement.executeUpdate("DELETE FROM " + toLowercaseFirst(modelClass.getSimpleName()) + " WHERE " + fields[0].getName() + " = " + id + ";");
    }

    public void add(T object) throws SQLException, IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        String tableName = toLowercaseFirst(modelClass.getSimpleName());
        sql.append("INSERT INTO ").append(tableName).append("(");
        Field[] fields = modelClass.getDeclaredFields();
        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            sql.append(fields[i].getName());
            sql.append(",");
        }
        sql = sql.deleteCharAt(sql.length() - 1);
        sql.append(") VALUE (");
        for (int i = 1; i < fields.length; i++) {
            sql.append("?,");
        }
        sql = sql.deleteCharAt(sql.length() - 1);
        sql.append(")");
        Object[] columns = new Object[fields.length];
        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            columns[i] = fields[i].get(object);
        }
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        for (int i = 1; i < fields.length; i++) {
            preparedStatement.setObject(i, columns[i]);
        }
        preparedStatement.executeUpdate();
    }

    public void update(T object) throws SQLException, IllegalAccessException {
        StringBuffer sql = new StringBuffer();
        String tableName = toLowercaseFirst(modelClass.getSimpleName());
        sql.append("UPDATE ").append(tableName).append(" SET ");
        Field[] fields = modelClass.getDeclaredFields();
        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true);
            sql.append(fields[i].getName());
            sql.append("=?,");
        }
        sql = sql.deleteCharAt(sql.length() - 1);
        sql.append(" WHERE ").append(fields[0].getName()).append("=?");
        Object[] columns = new Object[fields.length];
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            columns[i] = fields[i].get(object);
            if (isPrimitive(fields[i].getType())) {//check primitive type(Point 5)
                Class<?> boxed = boxPrimitiveClass(fields[i].getType());//box if primitive(Point 6)
                columns[i] = boxed.cast(columns[i]);
            }
        }
        System.out.println(sql.toString());
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
        for (int i = 1; i < fields.length; i++) {
            preparedStatement.setObject(i, columns[i]);
        }
        preparedStatement.setObject(fields.length, columns[0]);
        // ERROR 1452 (23000): Cannot add or update a child row: a foreign key constraint fails
        // (`yhtSaleSystem`.`user`, CONSTRAINT `STORE_FK` FOREIGN KEY (`storeID`) REFERENCES `store` (`storeID`))
        preparedStatement.executeUpdate();
    }


    public static boolean isPrimitive(Class<?> type) {
        return (type == int.class || type == long.class || type == double.class || type == float.class
                || type == boolean.class || type == byte.class || type == char.class || type == short.class);
    }

    public static Class<?> boxPrimitiveClass(Class<?> type) {
        if (type == int.class) {
            return Integer.class;
        } else if (type == long.class) {
            return Long.class;
        } else if (type == double.class) {
            return Double.class;
        } else if (type == float.class) {
            return Float.class;
        } else if (type == boolean.class) {
            return Boolean.class;
        } else if (type == byte.class) {
            return Byte.class;
        } else if (type == char.class) {
            return Character.class;
        } else if (type == short.class) {
            return Short.class;
        } else {
            String string = "class '" + type.getName() + "' is not a primitive";
            throw new IllegalArgumentException(string);
        }
    }
}
