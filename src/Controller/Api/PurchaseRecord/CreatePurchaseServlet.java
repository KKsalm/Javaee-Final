package Controller.Api.PurchaseRecord;

import Database.MaterialDataController;
import Database.PurchaseDataController;
import Model.Material;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;

@WebServlet(name = "CreatePurchaseServlet")
public class CreatePurchaseServlet extends HttpServlet {

    private static final String[] Message = {"Create Successfully", "Without Permission", "Empty Request"};
    private final Logger logger = LogManager.getLogger(getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        if (currentUser != null) {
            try {
                Date today = new Date();
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(today);
                calendar.add(Calendar.DATE, 1);
                today = calendar.getTime();
                today = sf.parse(sf.format(today));

                MaterialDataController materialDataController = new MaterialDataController();
                Material material = materialDataController.queryByID(Integer.parseInt(request.getParameter("materialID")));
                materialDataController.closeConnection();

                PurchaseRecord purchaseRecord = new PurchaseRecord(material.getMaterialID(), Integer.parseInt(request.getParameter("number")), Integer.parseInt(request.getParameter("number")) * material.getPrice(), today);
                PurchaseDataController purchaseDataController = new PurchaseDataController();
                purchaseDataController.add(purchaseRecord);
                purchaseDataController.closeConnection();

                session.setAttribute("Code", 0);
                session.setAttribute("Message", Message[0]);
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage());
            } catch (NamingException namingException) {
                logger.error(namingException.getMessage());
            } catch (IllegalAccessException illegalAccessException) {
                logger.error(illegalAccessException.getMessage());
            } catch (InstantiationException instantiationException) {
                logger.error(instantiationException);
            } catch (ParseException parseException) {
                logger.error(parseException);
            }
        } else {
            session.setAttribute("Code", 1);
            session.setAttribute("Message", Message[1]);
        }

        response.sendRedirect("/getCommodity.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
