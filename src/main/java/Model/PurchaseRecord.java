package main.java.Model;

import java.util.Date;

public class PurchaseRecord {

    private int purchaseRecordID;    // 数据库自增字段
    private float totalPrice;        // 总价格
    private Date date;             // 进货日期

    public PurchaseRecord() {
    }

    public PurchaseRecord(int purchaseRecordID, float totalPrice, Date date) {
        this.purchaseRecordID = purchaseRecordID;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public PurchaseRecord(float totalPrice, Date date) {
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public PurchaseRecord(int purchaseRecordID, float totalPrice) {
        this.purchaseRecordID = purchaseRecordID;
        this.totalPrice = totalPrice;
    }

    public int getPurchaseRecordID() {
        return purchaseRecordID;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setPurchaseRecordID(int purchaseRecordID) {
        this.purchaseRecordID = purchaseRecordID;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PurchaseRecord{" +
                "purchaseRecordID=" + purchaseRecordID +
                ", totalPrice=" + totalPrice +
                ", date='" + date + '\'' +
                '}';
    }
}
