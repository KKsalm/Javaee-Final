package Controller.Api.SaleAmount;

import Database.MonthlySaleAmountDataController;
import Model.MonthlySaleAmount;
import Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "GetSingleSaleAmountServlet")
public class GetMonthlySaleAmountServlet extends HttpServlet {

    private final String[] Message = {"Get saleAmount Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(GetDailySaleAmountServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) request.getAttribute("CurrentUser");

        if (currentUser != null) {
            if ("boss".equals(currentUser.getPosition()) || "manager".equals(currentUser.getPosition())) {
                try {
                    List<MonthlySaleAmount> monthlySaleAmounts = null;

                    MonthlySaleAmountDataController monthlySaleAmountDataController = new MonthlySaleAmountDataController();
                    monthlySaleAmounts = monthlySaleAmountDataController.queryAll();

                    for (MonthlySaleAmount monthlySaleAmount : monthlySaleAmounts) {
                        System.out.println(monthlySaleAmount.toString());
                    }

                    request.setAttribute("Products", monthlySaleAmounts);
                    request.setAttribute("Code", 0);
                    request.setAttribute("Message", Message[0]);
                } catch (SQLException sqlException) {
                    logger.error(sqlException.getMessage());
                } catch (NamingException namingException) {
                    logger.error(namingException.getMessage());
                } catch (IllegalAccessException illegalAccessException) {
                    logger.error(illegalAccessException.getMessage());
                } catch (InstantiationException instantiationException) {
                    logger.error(instantiationException.getMessage());
                }
            } else {
                request.setAttribute("Code", 1);
                request.setAttribute("Message", Message[1]);
            }
        } else {
            request.setAttribute("Code", 1);
            request.setAttribute("Message", Message[1]);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
