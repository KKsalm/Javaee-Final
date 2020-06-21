package Controller.Api.Store;

import Database.StoreDataController;
import Model.Store;
import Model.User;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "DeleteStoreController", urlPatterns = {"/DeleteStoreController"})
public class DeleteStoreController extends HttpServlet {

    private final String[] DeleteStoreMessage = {"Delete Store Successfully", "Store Does not Exist", "Without Permission"};
    private final Logger logger = LogManager.getLogger(DeleteStoreController.class);

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("User");

        Store store = new Store(Integer.parseInt(request.getParameter("storeID")));

        try {
            if (user != null) {
                if ("boss".equals(user.getPosition())) {
                    StoreDataController.deleteStore(store);
                    request.setAttribute("Code", 0);
                    request.setAttribute("Message", DeleteStoreMessage[0]);
                } else {
                    request.setAttribute("Code", 2);
                    request.setAttribute("Message", DeleteStoreMessage[2]);
                }
            }
        } catch (SQLException sqlException) {
            if (sqlException.getErrorCode() == 1054) {
                // Store Does not Exist
                logger.error("Store Does not Exist !");
                session.setAttribute("Code", 1);
                session.setAttribute("Message", DeleteStoreMessage[1]);
            }
            logger.error(sqlException.getMessage());
        }
    }

}
