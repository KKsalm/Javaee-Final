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
import java.text.SimpleDateFormat;
import java.util.Date;


public class CreatePurchaseRecordController extends HttpServlet {
    private static final String[] Message = {"Create Successfully", "Without Permission", "Empty Request"};
    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        if (currentUser != null) {
            if (req.getParameter("totalPrice") != null) {
                try {
                    Date today = new Date();
                    SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");

                    PurchaseRecord purchaseRecord = new PurchaseRecord(Integer.parseInt(req.getParameter("totalPrice")), today);
                    PurchaseRecordDataController purchaseRecordDataController = new PurchaseRecordDataController();
                    purchaseRecordDataController.add(purchaseRecord);

                    req.setAttribute("Code", 0);
                    req.setAttribute("Message", Message[0]);
                } catch (SQLException sqlException) {
                    logger.error(sqlException.getMessage());
                } catch (NamingException namingException) {
                    logger.error(namingException.getMessage());
                } catch (IllegalAccessException illegalAccessException) {
                    logger.error(illegalAccessException.getMessage());
                }
            } else {
                req.setAttribute("Code", 2);
                req.setAttribute("Message", Message[2]);
            }
        } else {
            req.setAttribute("Code", 1);
            req.setAttribute("Message", Message[1]);
        }


    }
}
