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

@WebServlet(name = "UpdatePurchaseServlet")
public class UpdatePurchaseServlet extends HttpServlet {

    private static final String[] Message = {"Update Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if (currentUser != null) {
                MaterialDataController materialDataController = new MaterialDataController();
                Material material = materialDataController.queryByID(Integer.parseInt(request.getParameter("materialID")));
                materialDataController.closeConnection();

                Date date = sf.parse(request.getParameter("date"));
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);
                calendar.add(Calendar.DATE, 1);
                date = calendar.getTime();

                PurchaseRecord purchaseRecord = new PurchaseRecord(Integer.parseInt(request.getParameter("purchaseRecordID")),
                        material.getMaterialID(), Integer.parseInt(request.getParameter("number")),
                        Integer.parseInt(request.getParameter("number")) * material.getPrice(), date);

                PurchaseDataController purchaseDataController = new PurchaseDataController();
                purchaseDataController.update(purchaseRecord);
                purchaseDataController.closeConnection();

                session.setAttribute("Code", 0);
                session.setAttribute("Message", Message[0]);
            } else {
                session.setAttribute("Code", 1);
                session.setAttribute("Message", Message[1]);
            }

        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } catch (NamingException namingException) {
            logger.error(namingException.getMessage());
        } catch (IllegalAccessException illegalAccessException) {
            logger.error(illegalAccessException.getMessage());
        } catch (InstantiationException instantiationException) {
            logger.error(instantiationException.getMessage());
        } catch (ParseException parseException) {
            logger.error(parseException.getMessage());
        }

        response.sendRedirect("/getCommodity.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
