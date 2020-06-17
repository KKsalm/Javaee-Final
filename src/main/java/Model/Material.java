package main.java.Model;

public class Material {

    private int materialID;         // 数据库自增字段
    private String materialName;    // 原料名称

    public Material() {
    }

    public Material(int materialID, String materialName) {
        this.materialID = materialID;
        this.materialName = materialName;
    }

    public int getMaterialID() {
        return materialID;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialID(int materialID) {
        this.materialID = materialID;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

}
