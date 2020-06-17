package main.java.Controller.Api.SaleRecord;

import main.java.Database.SaleRecordDataController;
import main.java.Model.SaleRecord;
import main.java.Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteSaleRecordController extends HttpServlet {

    private final String[] DeleteSaleRecordMessage = {"Delete SaleRecord Successfully", "SaleRecord Does not Exist"};
    private final Logger logger = LogManager.getLogger(DeleteSaleRecordController.class);

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        SaleRecord saleRecord = new SaleRecord(Integer.parseInt(request.getParameter("saleRecordID")));

        try {
            if (user != null) {
                SaleRecordDataController.deleteSaleRecord(saleRecord);
                request.setAttribute("Code", 0);
                request.setAttribute("Message", DeleteSaleRecordMessage[0]);
            }
        } catch (SQLException sqlException) {
            if (sqlException.getErrorCode() == 1054) {
                // SaleRecord Does not Exist
                logger.error("SaleRecord Does not Exist !");
                session.setAttribute("Code", 1);
                session.setAttribute("Message", DeleteSaleRecordMessage[1]);
                // Other Operations
            }
            logger.error(sqlException.getMessage());
        }
    }

}
