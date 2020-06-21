package main.java.Controller.Api.Product;

import main.java.Database.ProductDataController;
import main.java.Model.Product;
import main.java.Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateProductController extends HttpServlet {
    private static final String[] Message = {"Update Info Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(UpdateProductController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser != null) {
            try {
                Product product = new Product(Integer.parseInt(req.getParameter("productID")), req.getParameter("productName"), Float.parseFloat(req.getParameter("productPrice")));
                ProductDataController productDataController = new ProductDataController();
                productDataController.update(product);

                req.setAttribute("Code", 0);
                req.setAttribute("Message", Message[0]);
            }  catch (SQLException sqlException) {
                logger.error(sqlException.getMessage());
            } catch (NamingException namingException) {
                logger.error(namingException.getMessage());
            } catch (IllegalAccessException illegalAccessException) {
                logger.error(illegalAccessException.getMessage());
            }
        } else {
            req.setAttribute("Code", 1);
            req.setAttribute("Message", Message[1]);
        }

    }
}
