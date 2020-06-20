package main.java.Controller.Api.User;

import main.java.Controller.Api.Product.CreateProductController;
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
import java.util.List;

public class GetAllUserController extends HttpServlet {
    private static final String[] GetUsersMessage = {"Get Users Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(CreateProductController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("User");

        if (currentUser != null) {
            if ("staff".equals(currentUser.getPosition())) {
                req.setAttribute("Code", 1);
                req.setAttribute("Message", GetUsersMessage[1]);
            } else {
                try {
                    List<User> users = null;
                    UserDataController userDataController = new UserDataController();
                    users = userDataController.queryAll();

                    // System.out.println(users.get(0).toString());

                    req.setAttribute("UsersInfo", users);
                    req.setAttribute("Code", 0);
                    req.setAttribute("Message", GetUsersMessage[0]);
                } catch (SQLException sqlException) {
                    logger.error(sqlException.getMessage());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (NamingException namingException) {
                    namingException.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        }

         //req.getRequestDispatcher("").forward(req, resp);
    }
}
