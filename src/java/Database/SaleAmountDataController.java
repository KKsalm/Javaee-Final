package Database;

import Model.DailySaleAmount;
import Model.MonthlySaleAmount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.naming.NamingException;

public class SaleAmountDataController {

    private static Statement statement = null;

    public SaleAmountDataController() {
        super();
        //statement = super.getConnection().createStatement();
    }

}
