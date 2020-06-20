package main.java.Controller.Api.User;

import main.java.Controller.Api.Product.GetSingleProductController;
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

public class DeleteUserController extends HttpServlet {
    private static final String[] Message = {"Delete User Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(DeleteUserController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser != null) {
            if ("staff".equals(currentUser.getPosition())) {
                req.setAttribute("Code", 1);
                req.setAttribute("Message", Message[1]);
            } else {
                try {
                    UserDataController userDataController = new UserDataController();
                    User deleteUser = userDataController.queryByID(Integer.parseInt(req.getParameter("deleteID")));
                    if (("manager".equals(deleteUser.getPosition()) && "boss".equals(currentUser.getPosition()))
                        || "staff".equals(deleteUser.getPosition())) {
                        // Only Boss Can Fire Manager && Both Boss And Manager Can Fire Staff
                        userDataController.delete(Integer.parseInt(req.getParameter("deleteID")));
                        System.out.println(req.getParameter("deleteID"));
                        req.setAttribute("Code", 0);
                        req.setAttribute("Message", Message[0]);
                    } else {
                        req.setAttribute("Code", 1);
                        req.setAttribute("Message", Message[1]);
                    }
                } catch (SQLException sqlException) {
                    logger.error(sqlException.getMessage());
                } catch (NamingException namingException) {
                    logger.error(namingException.getMessage());
                } catch (ClassNotFoundException classNotFoundException) {
                    logger.error(classNotFoundException.getMessage());
                } catch (IllegalAccessException illegalAccessException) {
                    logger.error(illegalAccessException.getMessage());
                } catch (InstantiationException instantiationException) {
                    logger.error(instantiationException.getMessage());
                }
            }
        }

    }
}
