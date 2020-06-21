package main.java.Controller.Api.PurchaseRecord;

import main.java.Database.PurchaseRecordDataController;
import main.java.Model.PurchaseRecord;
import main.java.Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

public class UpdatePurchaseRecordController extends HttpServlet {
    private static final String[] Message = {"Update Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(req.getParameter("dateYear")), Integer.parseInt(req.getParameter("dateMonth")), Integer.parseInt(req.getParameter("dateDate")));

            PurchaseRecord purchaseRecord = new PurchaseRecord(Integer.parseInt(req.getParameter("purchaseRecordID")), Integer.parseInt(req.getParameter("totalPrice")) - 1, calendar.getTime());

            PurchaseRecordDataController purchaseRecordDataController = new PurchaseRecordDataController();
            purchaseRecordDataController.update(purchaseRecord);

            req.setAttribute("Code", 0);
            req.setAttribute("Message", Message[0]);
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } catch (NamingException namingException) {
            logger.error(namingException.getMessage());
        } catch (IllegalAccessException illegalAccessException) {
            logger.error(illegalAccessException.getMessage());
        }
    }
}
