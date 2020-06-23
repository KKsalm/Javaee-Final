package Controller.Api.PurchaseRecord;

import Database.PurchaseRecordDataController;
import Model.PurchaseRecord;
import Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

@WebServlet(name = "UpdatePurchaseServlet")
public class UpdatePurchaseServlet extends HttpServlet {
    private static final String[] Message = {"Update Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(request.getParameter("dateYear")), Integer.parseInt(request.getParameter("dateMonth")), Integer.parseInt(request.getParameter("dateDate")));

            PurchaseRecord purchaseRecord = new PurchaseRecord(Integer.parseInt(request.getParameter("purchaseRecordID")), Integer.parseInt(request.getParameter("totalPrice")) - 1, calendar.getTime());

            PurchaseRecordDataController purchaseRecordDataController = new PurchaseRecordDataController();
            purchaseRecordDataController.update(purchaseRecord);

            request.setAttribute("Code", 0);
            request.setAttribute("Message", Message[0]);
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } catch (NamingException namingException) {
            logger.error(namingException.getMessage());
        } catch (IllegalAccessException illegalAccessException) {
            logger.error(illegalAccessException.getMessage());
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
