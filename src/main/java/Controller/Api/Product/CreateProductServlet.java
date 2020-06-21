package main.java.Controller.Api.Product;

import main.java.Database.ProductDataController;
import main.java.Model.Product;
import main.java.Model.User;
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
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        if (currentUser != null) {
            if ("staff".equals(currentUser.getPosition())) {
                request.setAttribute("Code", 1);
                request.setAttribute("Message", Message[1]);
            } else {
                if (request.getParameter("productName") != null && request.getParameter("productPrice") != null) {
                    Product product = new Product(request.getParameter("productName"), Integer.parseInt(request.getParameter("productPrice")));

                    try {
                        ProductDataController productDataController = new ProductDataController();
                        productDataController.add(product);

                        request.setAttribute("Code", 0);
                        request.setAttribute("Message", Message[0]);
                    } catch (SQLException sqlException) {
                        if (sqlException.getErrorCode() == 1062) {
                            request.setAttribute("Code", 3);
                            request.setAttribute("Message", Message[3]);
                        }
                        logger.error(sqlException.getMessage());
                    } catch (NamingException namingException) {
                        logger.error(namingException.getMessage());
                    } catch (IllegalAccessException illegalAccessException) {
                        logger.error(illegalAccessException.getMessage());
                    }
                } else {
                    request.setAttribute("Code", 2);
                    request.setAttribute("Message", Message[2]);
                }
            }
        } else {
            request.setAttribute("Code", 1);
            request.setAttribute("Message", Message[1]);
        }

        request.getRequestDispatcher("").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
