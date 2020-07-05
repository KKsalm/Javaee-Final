package Model;

public class Material {

    private int materialID;         // 数据库自增字段
    private String materialName;    // 原料名称
    private float price;

    public Material() {
    }

    public Material(int materialID, String materialName) {
        this.materialID = materialID;
        this.materialName = materialName;
    }

    public Material(String materialName) {
        this.materialName = materialName;
    }

    public Material(int materialID, String materialName, float materialPrice) {
        this.materialID = materialID;
        this.materialName = materialName;
        this.price = materialPrice;
    }

    public Material(String materialName, float price) {
        this.materialName = materialName;
        this.price = price;
    }

    public int getMaterialID() {
        return materialID;
    }

    public String getMaterialName() {
        return materialName;
    }

    public float getPrice() {
        return price;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Material{" +
                "materialID=" + materialID +
                ", materialName='" + materialName + '\'' +
                ", price=" + price +
                '}';
    }
}
