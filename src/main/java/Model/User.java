package main.java.Model;

import main.java.Database.DatabaseController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User {
    protected int userId;               // 数据库自增字段
    protected int workNumber;           // 员工工号
    protected String name;              // 员工姓名
    protected String position;          // 员工职位
    protected int storeID;              // 店编号
    protected float monthWorkTime;      // 员工工作时间
    protected float monthSalary;        // 员工薪水
    protected String username;
    protected String password;


    public User() { }

    public User(int userId, int workNumber, String name, String position, int storeID, float monthWorkTime, float monthSalary) {
        this.userId = userId;
        this.workNumber = workNumber;
        this.name = name;
        this.position = position;
        this.storeID = storeID;
        this.monthWorkTime = monthWorkTime;
        this.monthSalary = monthSalary;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    // Get Part

    public int getUserId() {
        return userId;
    }

    public int getWorkNumber() {
        return workNumber;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getStoreID() {
        return storeID;
    }

    public float getMonthWorkTime() {
        return monthWorkTime;
    }

    public float getMonthSalary() {
        return monthSalary;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    // Set Part

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setWorkNumber(int workNumber) {
        this.workNumber = workNumber;
    }

    public void setName(String name) {
        name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public void setMonthWorkTime(float monthWorkTime) {
        this.monthWorkTime = monthWorkTime;
    }

    public void setMonthSalary(float monthSalary) {
        this.monthSalary = monthSalary;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Other Functions

}

