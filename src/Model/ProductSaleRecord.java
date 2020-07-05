package Model;

public class ProductSaleRecord {

    private int subSaleRecordID;    // 数据库自增字段
    private int saleRecordID;       // 订单记录ID
    private int productID;          // 商品ID
    private int productNum;         // 商品数量
    private float price;            // 总价格

    public int getSubSaleRecordID() {
        return subSaleRecordID;
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

    public float getPrice() {
        return price;
    }

    public void setSubSaleRecordID(int subSaleRecordID) {
        this.subSaleRecordID = subSaleRecordID;
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

    public void setPrice(float price) {
        this.price = price;
    }

}
