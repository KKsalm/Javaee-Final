package Model;

import java.util.Date;

public class PurchaseRecord {

    private int purchaseRecordID;    // 数据库自增字段
    private int materialID;             // 原料ID
    private int number;                 // 数量
    private float totalPrice;        // 总价格
    private Date date;             // 进货日期

    public PurchaseRecord() {
    }

    public PurchaseRecord(int materialID, float totalPrice, Date date) {
        this.materialID = materialID;
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

    public PurchaseRecord(int materialID, int number, float totalPrice, Date date) {
        this.materialID = materialID;
        this.number = number;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public PurchaseRecord(int purchaseRecordID, int materialID, int number, float totalPrice, Date date) {
        this.purchaseRecordID = purchaseRecordID;
        this.materialID = materialID;
        this.number = number;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public int getPurchaseRecordID() {
        return purchaseRecordID;
    }

    public int getMaterialID() {
        return materialID;
    }

    public int getNumber() {
        return number;
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

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PurchaseRecord{"
                + "purchaseRecordID=" + purchaseRecordID
                + ", totalPrice=" + totalPrice
                + ", date='" + date + '\''
                + '}';
    }
}
