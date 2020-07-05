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

@WebServlet(name = "UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {

    private static final String[] Message = {"Update Info Successfully", "Two Password are Not the Same"};
    private final Logger logger = LogManager.getLogger(getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        if (currentUser != null) {
            try {
                if (request.getParameter("userID") != null && request.getParameter("password") != null) {
                    if (!request.getParameter("validPassword").equals(request.getParameter("password"))) {

                        session.setAttribute("Code", 1);
                        session.setAttribute("Message", Message[1]);
                        response.sendRedirect("/myInfo.jsp");
                        return;
                    } else {

                        User user = new User(Integer.parseInt(request.getParameter("userID")), request.getParameter("name"),
                                request.getParameter("position"), Integer.parseInt(request.getParameter("storeID")),
                                Float.parseFloat(request.getParameter("workTime")), Float.parseFloat(request.getParameter("monthSalary")),
                                request.getParameter("username"), request.getParameter("password"));

                        UserDataController userDataController = new UserDataController();
                        userDataController.update(user);
                        userDataController.closeConnection();
                        session.setAttribute("Code", 0);
                        session.setAttribute("Message", Message[0]);
                    }
                }

            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage());
            } catch (NamingException namingException) {
                logger.error(namingException.getMessage());
            } catch (ClassNotFoundException classNotFoundException) {
                logger.error(classNotFoundException.getMessage());
            } catch (IllegalAccessException illegalAccessException) {
                logger.error(illegalAccessException.getMessage());
            }
        } else {
            session.setAttribute("Code", 1);
            session.setAttribute("Message", Message[1]);
        }
        response.sendRedirect("/myInfo.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
