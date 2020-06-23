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

@WebServlet(name = "GetSingleStoreServlet")
public class GetSingleStoreServlet extends HttpServlet {

    private static final String[] Message = {"Get Info Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        if (currentUser != null) {
            if ("boss".equals(currentUser.getPosition())) {
                try {
                    Store store = null;
                    StoreDataController storeDataController = new StoreDataController();
                    store = storeDataController.queryByID(Integer.parseInt(request.getParameter("queryID")));

                    request.setAttribute("ProductInfo", store);
                    request.setAttribute("Code", 0);
                    request.setAttribute("Message", Message[0]);
                } catch (SQLException sqlException) {
                    logger.error(sqlException.getMessage());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (NamingException namingException) {
                    namingException.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } else {
                request.setAttribute("Code", 1);
                request.setAttribute("Message", Message[1]);
            }
        } else {
            request.setAttribute("Code", 1);
            request.setAttribute("Message", Message[1]);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
