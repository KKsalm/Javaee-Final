package Controller.Auth;

import Database.SaleRecordDataController;
import Database.UserDataController;
import Model.SaleRecord;
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
import java.util.List;

@WebServlet(name = "RegisterController")
public class RegisterController extends HttpServlet {
    private final String[] Message = {"Register Successfully", "Username Already Exist", "Two Password are Not the Same"};
    private final Logger logger = LogManager.getLogger(RegisterController.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/register.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (req.getParameter("username") != null && req.getParameter("password") != null) {

            User user = new User(req.getParameter("username"), req.getParameter("password"));

            try {
                if (!req.getParameter("validPassword").equals(user.getPassword())) {
                    session.setAttribute("Code", 2);
                    session.setAttribute("Message", Message[2]);
                    resp.sendRedirect("/register.jsp");
                    return;
                }

                UserDataController userDataController = new UserDataController();
                userDataController.registerUser(user);

                session.setAttribute("Code", 0);
                session.setAttribute("Message", Message[0]);
                resp.sendRedirect("/login.jsp");
                return;
            } catch (SQLException sqlException) {
                if (sqlException.getErrorCode() == 1062) {
                    // Username already exist;
                    session.setAttribute("Code", 1);
                    session.setAttribute("Message", Message[1]);
                    // Other Operations

                }
                logger.error(sqlException.getMessage());
            } catch (NamingException namingException) {
                logger.error(namingException.getMessage());
            } catch (ClassNotFoundException classNotFoundException) {
                logger.error(classNotFoundException.getMessage());
            }
        }

        resp.sendRedirect("/register.jsp");
    }
}
