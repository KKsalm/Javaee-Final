package Controller.Api.Store;

import Database.StoreDataController;
import Model.Store;
import Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "CreateStoreController", urlPatterns = {"/CreateStoreController"})
public class CreateStoreController extends HttpServlet {

    private final String[] CreateStoreMessage = {"Create Store Successfully", "Store Already Exist", "Without Permission", "Empty Request"};
    private final Logger logger = LogManager.getLogger(CreateStoreController.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) request.getAttribute("User");

        if (request.getParameter("address") != null && request.getParameter("dayTurnover") != null && request.getParameter("monthTurnover") != null) {
            Store store = new Store(request.getParameter("address"),
                    Integer.parseInt(request.getParameter("dayTurnover")), Integer.parseInt(request.getParameter("monthTurnover")));

            try {
                if (user != null) {
                    if ("boss".equals(user.getPosition())) {
                        StoreDataController.createStore(store);
                        request.setAttribute("Code", 0);
                        request.setAttribute("Message", CreateStoreMessage[0]);
                    } else {
                        request.setAttribute("Code", 2);
                        request.setAttribute("Message", CreateStoreMessage[2]);
                    }
                }
            } catch (SQLException sqlException) {
                if (sqlException.getErrorCode() == 1062) {
                    request.setAttribute("Code", 1);
                    request.setAttribute("Message", CreateStoreMessage[1]);
                }
                logger.error(sqlException.getMessage());
            }
        } else {
            request.setAttribute("Code", 3);
            request.setAttribute("Message", CreateStoreMessage[3]);
        }

        request.getRequestDispatcher("").forward(request, response);
    }
}