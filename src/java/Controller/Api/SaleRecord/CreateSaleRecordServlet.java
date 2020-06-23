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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "CreateSaleRecordServlet")
public class CreateSaleRecordServlet extends HttpServlet {

    private final String[] Message = {"Create Successfully", "Empty Request", "Without Permission", "SaleRecord Already Exist"};
    private final Logger logger = LogManager.getLogger(CreateSaleRecordServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sf.parse(sf.format(date));
        } catch (ParseException parseException) {
            logger.error(parseException.getMessage());
        }

        if (currentUser != null) {
            if (request.getParameter("totalPrice") != null) {
                SaleRecord saleRecord = new SaleRecord(Integer.parseInt(request.getParameter("totalPrice")), date);

                try {
                    SaleRecordDataController saleRecordDataController = new SaleRecordDataController();
                    saleRecordDataController.add(saleRecord);

                    request.setAttribute("Code", 0);
                    request.setAttribute("Message", Message[0]);
                } catch (SQLException sqlException) {
                    if (sqlException.getErrorCode() == 1062) {
                        request.setAttribute("Code", 3);
                        request.setAttribute("Message", Message[3]);
                    }
                    logger.error(sqlException.getMessage());
                } catch (NamingException namingException) {
                    logger.error(namingException.getMessage());
                } catch (IllegalAccessException illegalAccessException) {
                    logger.error(illegalAccessException.getMessage());
                }
            } else {
                request.setAttribute("Code", 1);
                request.setAttribute("Message", Message[1]);
            }
        } else {
            request.setAttribute("Code", 2);
            request.setAttribute("Message", Message[2]);
        }

        request.getRequestDispatcher("").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
