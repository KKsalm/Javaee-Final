package Controller.Api.SaleRecord;

import Database.SaleRecordDataController;
import Model.SaleRecord;
import Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "GetSingleSaleRecordServlet")
public class GetSingleSaleRecordServlet extends HttpServlet {

    private final String[] Message = {"Get saleRecord Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(GetSingleSaleRecordServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser != null) {
            try {
                SaleRecord saleRecord = null;
                SaleRecordDataController saleRecordDataController = new SaleRecordDataController();
                saleRecord = saleRecordDataController.queryByID(Integer.parseInt(request.getParameter("queryID")));

                request.setAttribute("SaleRecordInfo", saleRecord);
                request.setAttribute("Code", 0);
                request.setAttribute("Message", Message[0]);
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NamingException namingException) {
                namingException.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("Code", 1);
            request.setAttribute("Message", Message[1]);
        }
    }

}
