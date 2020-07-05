package Controller.Api.Product;

import Database.ProductDataController;
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

@WebServlet(name = "DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {

    private static final String[] Message = {"Delete Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        if (currentUser != null) {
        try {
            ProductDataController productDataController = new ProductDataController();
            productDataController.delete(Integer.parseInt(request.getParameter("productID")));
            productDataController.closeConnection();

            session.setAttribute("Code", 0);
            session.setAttribute("Message", Message[0]);
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        } catch (NamingException namingException) {
            logger.error(namingException.getMessage());
        }

        } else {
            session.setAttribute("Code", 1);
            session.setAttribute("Message", Message[1]);
        }
        response.sendRedirect("/commodityManage.jsp");
    }
}
