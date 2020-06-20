package main.java.Model;

public class Product {

    private int productID;                 // 商品ID
    private String productName;            // 商品名
    private float productPrice;            // 商品单价

    public Product() {
    }

    public Product(int productID, String productName, float productPrice) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Product(String productName, float productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public Product(int productID) {
        this.productID = productID;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                '}';
    }
}
