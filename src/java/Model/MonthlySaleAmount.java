package Model;

public class MonthlySaleAmount {

    private int monthlySaleAmountID;      // 数据库自增字段
    private String yearMonth;             // 日期
    private float amount;                 // 销售额
    private int storeID;                  // 店编号

    public MonthlySaleAmount() {
    }

    public MonthlySaleAmount(int monthlySaleAmountID, String yearMonth, float amount) {
        this.monthlySaleAmountID = monthlySaleAmountID;
        this.yearMonth = yearMonth;
        this.amount = amount;
    }

    public MonthlySaleAmount(int storeID) {
        this.storeID = storeID;
    }

    public int getStoreID() {
        return storeID;
    }

    public int getMonthlySaleAmountID() {
        return monthlySaleAmountID;
    }

    public String getYearMonth() {
        return yearMonth;
    }

    public float getAmount() {
        return amount;
    }

    public void setMonthlySaleAmountID(int monthlySaleAmountID) {
        this.monthlySaleAmountID = monthlySaleAmountID;
    }

    public void setYearMonth(String yearMonth) {
        this.yearMonth = yearMonth;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MonthlySaleAmount{"
                + "monthlySaleAmountID=" + monthlySaleAmountID
                + ", yearMonth=" + yearMonth
                + ", amount=" + amount
                + ", storeID=" + storeID + '}';
    }

}
