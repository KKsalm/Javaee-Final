package Controller.Api.Product;

import Database.ProductDataController;
import Model.Product;
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

@WebServlet(name = "CreateProductServlet")
public class CreateProductServlet extends HttpServlet {

    private static final String[] Message = {"Create Successfully", "Without Permission", "Empty Request", "Product Name Already Exist"};
    private final Logger logger = LogManager.getLogger(getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        if (currentUser != null) {
            if (request.getParameter("productName") != null && request.getParameter("productPrice") != null) {
                Product product = new Product(request.getParameter("productName"), Integer.parseInt(request.getParameter("productPrice")));

                try {
                    ProductDataController productDataController = new ProductDataController();
                    productDataController.add(product);
                    productDataController.closeConnection();

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
        response.sendRedirect("/commodityManage.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
