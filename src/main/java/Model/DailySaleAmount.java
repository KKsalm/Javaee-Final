package main.java.Model;

public class DailySaleAmount {

    private int dailySaleAmountID;       // 数据库自增字段
    private String date;                 // 日期
    private float amount;                // 销售额

    public DailySaleAmount() {
    }

    public DailySaleAmount(int dailySaleAmountID, String date, float amount) {
        this.dailySaleAmountID = dailySaleAmountID;
        this.date = date;
        this.amount = amount;
    }

    public int getDailySaleAmountID() {
        return dailySaleAmountID;
    }

    public String getDate() {
        return date;
    }

    public float getAmount() {
        return amount;
    }

    public void setDailySaleAmountID(int dailySaleAmountID) {
        this.dailySaleAmountID = dailySaleAmountID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

}
