package main.java.Controller.Api.PurchaseRecord;

import main.java.Database.PurchaseRecordDataController;
import main.java.Model.PurchaseRecord;
import main.java.Model.User;
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
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "CreatePurchaseServlet")
public class CreatePurchaseServlet extends HttpServlet {
    private static final String[] Message = {"Create Successfully", "Without Permission", "Empty Request"};
    private final Logger logger = LogManager.getLogger(getClass());
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        if (currentUser != null) {
            if (request.getParameter("totalPrice") != null) {
                try {
                    Date today = new Date();
                    SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd");

                    PurchaseRecord purchaseRecord = new PurchaseRecord(Integer.parseInt(request.getParameter("totalPrice")), today);
                    PurchaseRecordDataController purchaseRecordDataController = new PurchaseRecordDataController();
                    purchaseRecordDataController.add(purchaseRecord);

                    request.setAttribute("Code", 0);
                    request.setAttribute("Message", Message[0]);
                } catch (SQLException sqlException) {
                    logger.error(sqlException.getMessage());
                } catch (NamingException namingException) {
                    logger.error(namingException.getMessage());
                } catch (IllegalAccessException illegalAccessException) {
                    logger.error(illegalAccessException.getMessage());
                }
            } else {
                request.setAttribute("Code", 2);
                request.setAttribute("Message", Message[2]);
            }
        } else {
            request.setAttribute("Code", 1);
            request.setAttribute("Message", Message[1]);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
