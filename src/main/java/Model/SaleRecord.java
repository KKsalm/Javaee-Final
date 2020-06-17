package main.java.Model;

public class SaleRecord {

    private int saleRecordID;        // 数据库自增字段
    private float totalPrice;        // 总价格
    private String date;             // 订单日期

    public SaleRecord() {
    }

    public SaleRecord(int saleRecordID) {
        this.saleRecordID = saleRecordID;
    }

    public SaleRecord(float totalPrice, String date) {
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public SaleRecord(int saleRecordID, float totalPrice, String date) {
        this.saleRecordID = saleRecordID;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public int getSaleRecordID() {
        return saleRecordID;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public String getDate() {
        return date;
    }

    public void setSaleRecordID(int saleRecordID) {
        this.saleRecordID = saleRecordID;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
