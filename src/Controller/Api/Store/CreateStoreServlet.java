package Controller.Api.Store;

import Database.StoreDataController;
import Model.Store;
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
import java.util.logging.Level;

@WebServlet(name = "CreateStoreServlet")
public class CreateStoreServlet extends HttpServlet {

    private static final String[] Message = {"Create Successfully", "Without Permission", "Empty Request", "Store Already Exist"};
    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        if (currentUser != null) {
            if ("boss".equals(currentUser.getPosition())) {
                if (request.getParameter("address") != null) {
                    Store store = new Store(request.getParameter("address"));
                    try {
                        StoreDataController storeDataController = new StoreDataController();
                        storeDataController.add(store);
                        storeDataController.closeConnection();

                        session.setAttribute("Code", 0);
                        session.setAttribute("Message", Message[0]);
                    } catch (SQLException sqlException) {
                        if (sqlException.getErrorCode() == 1062) {
                            session.setAttribute("Code", 3);
                            session.setAttribute("Message", Message[3]);
                        }
                        logger.error(sqlException.getMessage());
                    } catch (NamingException namingException) {
                        logger.error(namingException.getMessage());
                    } catch (IllegalAccessException illegalAccessException) {
                        logger.error(illegalAccessException.getMessage());
                    }
                } else {
                    session.setAttribute("Code", 2);
                    session.setAttribute("Message", Message[2]);
                }
            } else {
                session.setAttribute("Code", 1);
                session.setAttribute("Message", Message[1]);
            }
        } else {
            session.setAttribute("Code", 1);
            session.setAttribute("Message", Message[1]);
        }
        response.sendRedirect("/storeManage.jsp");

    }
}
