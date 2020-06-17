package main.java.Model;

public class PurchaseRecord {

    private int purchaseRecordID;    // 数据库自增字段
    private float totalPrice;        // 总价格
    private String date;             // 进货日期

    public PurchaseRecord() {
    }

    public PurchaseRecord(int purchaseRecordID, float totalPrice, String date) {
        this.purchaseRecordID = purchaseRecordID;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public int getPurchaseRecordID() {
        return purchaseRecordID;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setPurchaseRecordID(int purchaseRecordID) {
        this.purchaseRecordID = purchaseRecordID;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDate(String date) {
        this.date = date;
    }

}

class dayPurchaseRecord {

}

class monthPurchaseRecord {

}
