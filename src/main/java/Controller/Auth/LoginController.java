package main.java.Controller.Auth;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

import main.java.Database.UserDataController;
import main.java.Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginController extends HttpServlet {
    private final String[] loginMessage = {"Login Successfully", "Empty Request", "Error Password or Username", "Username Does not Exist"};
    private final Logger logger = LogManager.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("username"), req.getParameter("password"));

        HttpSession session = req.getSession();

        if (!user.getUsername().equals("") && !user.getPassword().equals("")) {
            try {

                String queryResult = UserDataController.getPassword(user);
                UserDataController.closeConnection();

                if (queryResult != null ) {
                    if (user.getPassword().equals(queryResult)) {
                        // Put User Object into Sessions
                        session.setAttribute("Code", 0);
                        session.setAttribute("Message",loginMessage[0]);
//                    Cookie[] cookie = {new Cookie(user)}
//                    resp.addCookie();
                        session.setAttribute("User", user);
                    } else {
                        session.setAttribute("Code", 3);
                        session.setAttribute("Message",loginMessage[3]);
                    }
                } else {
                    session.setAttribute("Code", 4);
                    session.setAttribute("Message",loginMessage[4]);
                    logger.error(loginMessage[4]);
                }
            } catch (SQLException sqlException) {
                if (sqlException.getErrorCode() == 1054) {
                    // User Does not Exist
                    session.setAttribute("Code", 4);
                    session.setAttribute("Message",loginMessage[4]);
                    logger.error(loginMessage[4]);

                }
                logger.fatal(sqlException.getMessage());
            }
        } else {
            session.setAttribute("Code", 1);
            session.setAttribute("Message",loginMessage[1]);
            resp.sendRedirect("/login.jsp");
        }
    }
}
