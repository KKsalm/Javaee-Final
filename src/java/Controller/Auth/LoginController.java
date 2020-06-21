package Controller.Auth;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

import Database.UserDataController;
import Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginController extends HttpServlet {
    private final String[] loginMessage = {"Login Successfully", "Empty Request", "Error Password or Username", "Username Does not Exist"};
    private final Logger logger = LogManager.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            UserDataController userDataController = new UserDataController();
            User user = userDataController.queryByID(1);
            System.out.println(user.getUsername());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (NamingException namingException) {
            namingException.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

//        resp.sendRedirect("/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("username"), req.getParameter("password"));
        HttpSession session = req.getSession();

        if (!user.getUsername().equals("") && !user.getPassword().equals("")) {
            try {
                UserDataController userDataController = new UserDataController();
                String queryResult = userDataController.getPassword(user);

                if (queryResult != null ) {
                    if (user.getPassword().equals(queryResult)) {
                        resp.addCookie(new Cookie("username", user.getUsername()));
                        resp.addCookie(new Cookie("password", user.getPassword()));
                        // Put User Object into Sessions
                        session.setAttribute("User", user);
                        session.setAttribute("Code", 0);
                        session.setAttribute("Message",loginMessage[0]);
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
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (NamingException namingException) {
                namingException.printStackTrace();
            }
        } else {
            session.setAttribute("Code", 1);
            session.setAttribute("Message",loginMessage[1]);
            resp.sendRedirect("/login.jsp");
        }
    }
}
