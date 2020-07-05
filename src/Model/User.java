package Model;

public class User {

    protected int userID;               // 数据库自增字段
    protected String name;              // 员工姓名
    protected String position;          // 员工职位
    protected Integer storeID;              // 店编号
    protected float monthWorkTime;      // 员工工作时间
    protected float monthSalary;        // 员工薪水
    protected String username;
    protected String password;

    public User() {
    }

    public User(int userID, String position, Integer storeID, float monthWorkTime, float monthSalary, String username, String password) {
        this.userID = userID;
        this.position = position;
        this.storeID = storeID;
        this.monthWorkTime = monthWorkTime;
        this.monthSalary = monthSalary;
        this.username = username;
        this.password = password;
    }

    public User(int userID, String position, Integer storeID, float monthWorkTime, float monthSalary) {
        this.userID = userID;
        this.position = position;
        this.storeID = storeID;
        this.monthWorkTime = monthWorkTime;
        this.monthSalary = monthSalary;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(int userID, String password) {
        this.userID = userID;
        this.password = password;
    }

    public User(int parseInt, String parameter, String parameter0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User(int userID, String name, String position, int storeID, float workTime, float monthSalary, String username, String password) {
        this.userID = userID;
        this.name = name;
        this.position = position;
        this.storeID = storeID;
        this.monthWorkTime = workTime;
        this.monthSalary = monthSalary;
        this.username = username;
        this.password = password;
    }

    // Get Part
    public int getUserID() {
        return userID;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public Integer getStoreID() {
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
    public void setUserID(int userId) {
        this.userID = userId;
    }

    public void setName(String name) {
        name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStoreID(Integer storeID) {
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

    @Override
    public String toString() {
        return "User{"
                + "userId=" + userID
                + ", name='" + name + '\''
                + ", position='" + position + '\''
                + ", storeID=" + storeID
                + ", monthWorkTime=" + monthWorkTime
                + ", monthSalary=" + monthSalary
                + ", username='" + username + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
