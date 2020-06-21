package Controller.Auth;

import Database.DatabaseController;
import Database.UserDataController;
import Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterController extends HttpServlet {
    private final String[] RegisterMessage = {"Register Successfully", "Username Already Exist"};
    private final Logger logger = LogManager.getLogger(RegisterController.class);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//
//        if (req.getParameter("username") != null && req.getParameter("password") != null) {
//            User user = new User(req.getParameter("username"), req.getParameter("password"));
//            System.out.println(req.getParameter("username") + " " + req.getParameter("password"));
//
//            try {
//                UserDataController userDataController = new UserDataController();
//                userDataController.registerUser(user);
//                userDataController.closeConnection();
//
//                session.setAttribute("Code", 0);
//                session.setAttribute("Message", RegisterMessage[0]);
//            } catch (SQLException sqlException) {
//                if (sqlException.getErrorCode() == 1062) {
//                    // Username already exist;
//                    session.setAttribute("Code", 1);
//                    session.setAttribute("Message", RegisterMessage[1]);
//                    // Other Operations
//                    sqlException.printStackTrace();
//                } else {
//                    logger.fatal(sqlException.getMessage());
//                }
//            } catch (ClassNotFoundException classNotFoundException) {
//                classNotFoundException.printStackTrace();
//            }
//        }

    }
}
