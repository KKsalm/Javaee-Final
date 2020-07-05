package Model;

import java.util.Date;

public class SaleRecord {

    private int saleRecordID;    // 数据库自增字段
    private int productID;          // 商品ID
    private int productNum;         // 商品数量
    private float totalPrice;            // 总价格
    private int storeID;
    private Date date;

    public SaleRecord(float totalPrice, Date date, int storeID) {
        this.totalPrice = totalPrice;
        this.date = date;
        this.storeID = storeID;
    }

    public SaleRecord() {

    }

    public SaleRecord(int saleRecordID, int productID, int productNum, float totalPrice, Integer storeID, Date date) {
        this.saleRecordID = saleRecordID;
        this.productID = productID;
        this.productNum = productNum;
        this.totalPrice = totalPrice;
        this.storeID = storeID;
        this.date = date;
    }

    public SaleRecord(int productID, int productNum, float totalPrice, Integer storeID, Date date) {
        this.productID = productID;
        this.productNum = productNum;
        this.totalPrice = totalPrice;
        this.storeID = storeID;
        this.date = date;
    }

    public SaleRecord(int saleRecordID, float totalPrice, Date date) {
        this.saleRecordID = saleRecordID;
        this.totalPrice = totalPrice;
        this.date = date;
    }


    public int getSaleRecordID() {
        return saleRecordID;
    }

    public int getProductID() {
        return productID;
    }

    public int getProductNum() {
        return productNum;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public int getStoreID() {
        return storeID;
    }

    public Date getDate() {
        return date;
    }

    public void setSaleRecordID(int saleRecordID) {
        this.saleRecordID = saleRecordID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductNum(int productNum) {
        this.productNum = productNum;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SubSaleRecord{" +
                "saleRecordID=" + saleRecordID +
                ", saleRecordID=" + saleRecordID +
                ", productID=" + productID +
                ", productNum=" + productNum +
                ", price=" + totalPrice +
                '}';
    }
}
