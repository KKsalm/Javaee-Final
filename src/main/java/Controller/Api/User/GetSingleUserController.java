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

public class GetSingleUserController extends HttpServlet {
    private static final String[] Message = {"Get Info Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(GetSingleUserController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("User");

        if (currentUser != null) {
            if ("staff".equals(currentUser.getPosition())) {
                req.setAttribute("Code", 1);
                req.setAttribute("Message", Message[1]);
            } else {
                try {
                    User user = null;
                    UserDataController userDataController = new UserDataController();
                    user = userDataController.queryByID(Integer.parseInt(req.getParameter("queryID")));

                    // System.out.println(req.getParameter("queryID"));
                    // System.out.println(user.toString());

                    req.setAttribute("UserInfo", user);
                    req.setAttribute("Code", 0);
                    req.setAttribute("Message", Message[0]);
                } catch (SQLException sqlException) {
                    logger.error(sqlException.getMessage());
                } catch (InstantiationException instantiationException) {
                    logger.error(instantiationException.getMessage());
                } catch (NamingException namingException) {
                    logger.error(namingException.getMessage());
                } catch (IllegalAccessException illegalAccessException) {
                    logger.error(illegalAccessException.getMessage());
                } catch (ClassNotFoundException classNotFoundException) {
                    logger.error(classNotFoundException.getMessage());
                }
            }
        }

        //req.getRequestDispatcher("").forward(req, resp);
    }
}
