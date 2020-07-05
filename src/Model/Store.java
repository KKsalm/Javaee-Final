package Model;

public class Store {

    private int storeID;              // 店铺ID
    private String address;           // 店铺地址

    public Store() {
    }

    public Store(int storeID, String address) {
        this.storeID = storeID;
        this.address = address;
    }

    public Store(String address) {
        this.address = address;
    }

    public int getStoreID() {
        return storeID;
    }

    public String getAddress() {
        return address;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Store{"
                + "storeID=" + storeID
                + ", address=" + address + '}';
    }

}
