package Model;

public class MonthlySaleAmount {

    private int monthlySaleAmountID;      // 数据库自增字段
    private String yearMonth;             // 日期
    private float amount;                 // 销售额

    public MonthlySaleAmount() {
    }

    public MonthlySaleAmount(int monthlySaleAmountID, String yearMonth, float amount) {
        this.monthlySaleAmountID = monthlySaleAmountID;
        this.yearMonth = yearMonth;
        this.amount = amount;
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

}
