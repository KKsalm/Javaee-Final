package main.java.Model;

public class SubPurchaseRecord {

    private int recordID;               // 数据库自增字段
    private int purchaseRecordID;       // 进货记录ID
    private int materialID;             // 原料ID
    private int number;                 // 数量
    private float price;                // 单价

    public SubPurchaseRecord() {
    }

    public SubPurchaseRecord(int recordID, int purchaseRecordID, int materialID, int number, float price) {
        this.recordID = recordID;
        this.purchaseRecordID = purchaseRecordID;
        this.materialID = materialID;
        this.number = number;
        this.price = price;
    }

    public int getRecordID() {
        return recordID;
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

    public float getPrice() {
        return price;
    }

    public void setRecordID(int recordID) {
        this.recordID = recordID;
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

    public void setPrice(float price) {
        this.price = price;
    }

}
