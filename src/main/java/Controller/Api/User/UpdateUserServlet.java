package main.java.Controller.Api.User;

import main.java.Database.UserDataController;
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

@WebServlet(name = "UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    private static final String[] Message = {"Update Info Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("User");

        if (currentUser != null) {
            try {
                User user = new User(Integer.parseInt(request.getParameter("userID")), new Integer(request.getParameter("workNumber")), request.getParameter("name"), request.getParameter("position"),
                        new Integer(request.getParameter("storeID")), Float.parseFloat(request.getParameter("monthWorkTime")), Float.parseFloat(request.getParameter("monthSalary")), request.getParameter("username"),
                        request.getParameter("password"));
                UserDataController userDataController = new UserDataController();
                userDataController.update(user);

                request.setAttribute("UserInfo", user);
                request.setAttribute("Code", 0);
                request.setAttribute("Message", Message[0]);
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage());
            } catch (NamingException namingException) {
                logger.error(namingException.getMessage());
            } catch (IllegalAccessException illegalAccessException) {
                logger.error(illegalAccessException.getMessage());
            } catch (ClassNotFoundException classNotFoundException) {
                logger.error(classNotFoundException.getMessage());
            }
        } else {
            request.setAttribute("Code", 1);
            request.setAttribute("Message", Message[1]);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
