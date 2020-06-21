package Model;

public class Store {

    private int storeID;              // 店铺ID
    private String address;           // 店铺地址
    private float dayTurnover;        // 日营销额
    private float monthTurnover;      // 月营销额

    public Store() {
    }

    public Store(int storeID, String address, float dayTurnover, float monthTurnover) {
        this.storeID = storeID;
        this.address = address;
        this.dayTurnover = dayTurnover;
        this.monthTurnover = monthTurnover;
    }

    public Store(int storeID) {
        this.storeID = storeID;
    }

    public Store(String address, float dayTurnover, float monthTurnover) {
        this.address = address;
        this.dayTurnover = dayTurnover;
        this.monthTurnover = monthTurnover;
    }

    public int getStoreID() {
        return storeID;
    }

    public String getAddress() {
        return address;
    }

    public float getDayTurnover() {
        return dayTurnover;
    }

    public float getMonthTurnover() {
        return monthTurnover;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDayTurnover(float dayTurnover) {
        this.dayTurnover = dayTurnover;
    }

    public void setMonthTurnover(float monthTurnover) {
        this.monthTurnover = monthTurnover;
    }

}
