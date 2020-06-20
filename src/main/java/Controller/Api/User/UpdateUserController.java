package main.java.Controller.Api.User;

import main.java.Database.UserDataController;
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

public class UpdateUserController extends HttpServlet {
    private static final String[] Message = {"Get Users Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(UpdateUserController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("User");

        if (currentUser != null) {
            try {
                User user = new User(Integer.parseInt(req.getParameter("userID")), new Integer(req.getParameter("workNumber")), req.getParameter("name"), req.getParameter("position"),
                        new Integer(req.getParameter("storeID")), Float.parseFloat(req.getParameter("monthWorkTime")), Float.parseFloat(req.getParameter("monthSalary")), req.getParameter("username"),
                        req.getParameter("password"));
                UserDataController userDataController = new UserDataController();
                userDataController.update(user);

                req.setAttribute("UserInfo", user);
                req.setAttribute("Code", 0);
                req.setAttribute("Message", Message[0]);
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
            req.setAttribute("Code", 1);
            req.setAttribute("Message", Message[1]);
        }


    }
}
