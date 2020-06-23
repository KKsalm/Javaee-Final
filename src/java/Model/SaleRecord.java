package Model;

import java.util.Date;

public class SaleRecord {

    private int saleRecordID;        // 数据库自增字段
    private float totalPrice;        // 总价格
    private Date date;               // 订单日期
    private int storeID;         // 店编号

    public SaleRecord() {
    }

    public SaleRecord(int saleRecordID) {
        this.saleRecordID = saleRecordID;
    }

    public SaleRecord(float totalPrice, Date date) {
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public SaleRecord(float totalPrice, Date date, int storeID) {
        this.totalPrice = totalPrice;
        this.date = date;
        this.storeID = storeID;
    }

    public SaleRecord(int saleRecordID, float totalPrice, Date date) {
        this.saleRecordID = saleRecordID;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public SaleRecord(int saleRecordID, float totalPrice, Date date, int storeID) {
        this.saleRecordID = saleRecordID;
        this.totalPrice = totalPrice;
        this.date = date;
        this.storeID = storeID;
    }

    public int getSaleRecordID() {
        return saleRecordID;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setSaleRecordID(int saleRecordID) {
        this.saleRecordID = saleRecordID;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SaleRecord{"
                + "saleRecordID=" + saleRecordID
                + ", totalPrice=" + totalPrice
                + ", date=" + date
                + ", storeID=" + storeID + '}';
    }

}
