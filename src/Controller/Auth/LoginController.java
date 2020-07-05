package Controller.Auth;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

import Database.UserDataController;
import Model.SaleRecord;
import Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "LoginController")
public class LoginController extends HttpServlet {
    private final String[] Message = {"Login Successfully", "Empty Request", "Error Password or Username", "Username Does not Exist"};
    private final Logger logger = LogManager.getLogger(LoginController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");
        if (currentUser != null) {
            resp.sendRedirect("/index.jsp");
            return;
        }
        resp.sendRedirect("/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("username"), req.getParameter("password"));
        HttpSession session = req.getSession();

        if ((user.getUsername() != null && user.getPassword() != null) ||
                (!user.getUsername().equals("") && !user.getPassword().equals(""))) {
            try {
                UserDataController userDataController = new UserDataController();
                String queryResult = userDataController.queryByUsername(user.getUsername()).getPassword();

                if (queryResult != null ) {
                    if (user.getPassword().equals(queryResult)) {
                        // 一般情况使用md5 加 时间戳之后 加密放入cookie中，此处偷工减料
                        resp.addCookie(new Cookie("username", user.getUsername()));
                        resp.addCookie(new Cookie("password", user.getPassword()));

                        // Put User Object into Sessions
                        user = userDataController.queryByUsername(user.getUsername());
                        userDataController.closeConnection();

                        session.setAttribute("CurrentUser", user);
                        session.setAttribute("Code", 0);
                        session.setAttribute("Message",Message[0]);

                        resp.sendRedirect("/index.jsp");
                        return;
                    } else {
                        session.setAttribute("Code", 2);
                        session.setAttribute("Message",Message[2]);
                    }
                } else {
                    session.setAttribute("Code", 3);
                    session.setAttribute("Message",Message[3]);
                }
            } catch (SQLException sqlException) {
                if (sqlException.getErrorCode() == 1054) {
                    // User Does not Exist
                    session.setAttribute("Code", 3);
                    session.setAttribute("Message",Message[3]);

                }
                logger.fatal(sqlException.getMessage());
            } catch (ClassNotFoundException classNotFoundException) {
                logger.error(classNotFoundException.getMessage());
            } catch (NamingException namingException) {
                logger.error(namingException.getMessage());
            } catch (IllegalAccessException illegalAccessException) {
                logger.error(illegalAccessException.getMessage());
            } catch (InstantiationException instantiationException) {
                logger.error(instantiationException.getMessage());
            }
        } else {
            session.setAttribute("Code", 1);
            session.setAttribute("Message",Message[1]);
        }

        resp.sendRedirect("/login.jsp");
    }
}
