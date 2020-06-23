package Model;

import java.util.Date;

public class DailySaleAmount {

    private int dailySaleAmountID;       // 数据库自增字段
    private Date date;                   // 日期
    private float amount;                // 销售额
    private int storeID;                 // 店编号

    public DailySaleAmount() {
    }

    public DailySaleAmount(int dailySaleAmountID, Date date, float amount) {
        this.dailySaleAmountID = dailySaleAmountID;
        this.date = date;
        this.amount = amount;
    }

    public int getDailySaleAmountID() {
        return dailySaleAmountID;
    }

    public Date getDate() {
        return date;
    }

    public float getAmount() {
        return amount;
    }

    public void setDailySaleAmountID(int dailySaleAmountID) {
        this.dailySaleAmountID = dailySaleAmountID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

}
