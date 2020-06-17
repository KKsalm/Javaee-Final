package main.java.Controller.Api.Product;

import main.java.Database.ProductDataController;
import main.java.Model.Product;
import main.java.Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteProductController extends HttpServlet {
    private static final String[] DeleteProductMessage = {"Delete Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(DeleteProductController.class);

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");

        Product product = new Product(Integer.parseInt(req.getParameter("productID")));

        try {
            if (user != null) {
                if ("staff".equals(user.getPosition())) {
                    req.setAttribute("Code", 1);
                    req.setAttribute("Message", DeleteProductMessage[1]);
                } else {
                    ProductDataController.deleteProduct(product);
                    req.setAttribute("Code", 0);
                    req.setAttribute("Message", DeleteProductMessage[0]);
                }
            }
        } catch (SQLException sqlException) {
            logger.error(sqlException.getMessage());
        }
    }
}
