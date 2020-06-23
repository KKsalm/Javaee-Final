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

@WebServlet(name = "GetSingleUserServlet")
public class GetSingleUserServlet extends HttpServlet {
    private static final String[] Message = {"Get Info Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("User");

        if (currentUser != null) {
            if ("staff".equals(currentUser.getPosition())) {
                request.setAttribute("Code", 1);
                request.setAttribute("Message", Message[1]);
            } else {
                try {
                    User user = null;
                    UserDataController userDataController = new UserDataController();
                    user = userDataController.queryByID(Integer.parseInt(request.getParameter("queryID")));

                    // System.out.println(request.getParameter("queryID"));
                    // System.out.println(user.toString());

                    request.setAttribute("UserInfo", user);
                    request.setAttribute("Code", 0);
                    request.setAttribute("Message", Message[0]);
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
    }
}
