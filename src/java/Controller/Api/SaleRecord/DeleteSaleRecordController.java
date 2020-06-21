package Controller.Api.SaleRecord;

import Database.SaleRecordDataController;
import Model.SaleRecord;
import Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "DeleteSaleRecordController", urlPatterns = {"/DeleteSaleRecordController"})
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
            }
            logger.error(sqlException.getMessage());
        }
    }

}