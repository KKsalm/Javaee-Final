package Controller.Api.SaleAmount;

import Database.DailySaleAmountDataController;
import Model.DailySaleAmount;
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

@WebServlet(name = "CreateDailySaleAmountServlet")
public class CreateDailySaleAmountServlet extends HttpServlet {

    private final String[] Message = {"Create Successfully", "Empty Request", "Without Permission", "SaleRecord Already Exist"};
    private final Logger logger = LogManager.getLogger(CreateDailySaleAmountServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        if (currentUser != null) {
            if ("boss".equals(currentUser.getPosition())|| "manager".equals(currentUser.getPosition())) {
                if (request.getParameter("storeID") != null) {
                    try {
                        DailySaleAmountDataController dailySaleAmountDataController = new DailySaleAmountDataController();
                        dailySaleAmountDataController.createSaleAmount(Integer.parseInt(request.getParameter("storeID")));

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
                    }
                } else {
                    request.setAttribute("Code", 2);
                    request.setAttribute("Message", Message[2]);
                }
            } else {
                request.setAttribute("Code", 1);
                request.setAttribute("Message", Message[1]);
            }
        } else {
            request.setAttribute("Code", 1);
            request.setAttribute("Message", Message[1]);
        }

        request.getRequestDispatcher("").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
