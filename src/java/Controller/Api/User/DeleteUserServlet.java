package Controller.Api.User;

import Database.UserDataController;
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

@WebServlet(name = "DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
    private static final String[] Message = {"Delete Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser != null) {
            if ("staff".equals(currentUser.getPosition())) {
                request.setAttribute("Code", 1);
                request.setAttribute("Message", Message[1]);
            } else {
                try {
                    UserDataController userDataController = new UserDataController();
                    User deleteUser = userDataController.queryByID(Integer.parseInt(request.getParameter("deleteID")));
                    if (("manager".equals(deleteUser.getPosition()) && "boss".equals(currentUser.getPosition()))
                            || "staff".equals(deleteUser.getPosition())) {
                        // Only Boss Can Fire Manager && Both Boss And Manager Can Fire Staff
                        userDataController.delete(Integer.parseInt(request.getParameter("deleteID")));
                        System.out.println(request.getParameter("deleteID"));
                        request.setAttribute("Code", 0);
                        request.setAttribute("Message", Message[0]);
                    } else {
                        request.setAttribute("Code", 1);
                        request.setAttribute("Message", Message[1]);
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
