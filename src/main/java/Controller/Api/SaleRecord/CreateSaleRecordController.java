package main.java.Controller.Api.SaleRecord;

import main.java.Database.SaleRecordDataController;
import main.java.Model.SaleRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class CreateSaleRecordController extends HttpServlet {

    private final String[] CreateSaleSaleRecordMessage = {"Create SaleRecord Successfully", "SaleRecord Already Exist", "Empty Request"};
    private final Logger logger = LogManager.getLogger(CreateSaleRecordController.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (request.getParameter("productName") != null && request.getParameter("productPrice") != null) {
            SaleRecord saleRecord = new SaleRecord(Integer.parseInt(request.getParameter("totalPrice")), request.getParameter("date"));

            try {
                SaleRecordDataController.createSaleRecord(saleRecord);

                request.setAttribute("Code", 0);
                request.setAttribute("Message", CreateSaleSaleRecordMessage[0]);
            } catch (SQLException sqlException) {
                if (sqlException.getErrorCode() == 1062) {
                    request.setAttribute("Code", 1);
                    request.setAttribute("Message", CreateSaleSaleRecordMessage[1]);
                }
                logger.error(sqlException.getMessage());
            }
        } else {
            request.setAttribute("Code", 2);
            request.setAttribute("Message", CreateSaleSaleRecordMessage[2]);
        }

        request.getRequestDispatcher("").forward(request, response);
    }

}
