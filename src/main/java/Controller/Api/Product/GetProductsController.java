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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetProductsController extends HttpServlet {
    private static final String[] ProductMessage = {"Get Data Success", "Without Permission"};
    private final Logger logger = LogManager.getLogger(GetProductsController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        User user = (User) req.getAttribute("User");

        if (user != null) {
            try {
                List<Product> products = new ArrayList<>();

                ResultSet resultSet = ProductDataController.getProducts();
                ProductDataController.closeConnection();

                while (resultSet.next()) {
                    Product product = new Product(resultSet.getInt("productID"), resultSet.getString("productName"), resultSet.getFloat("productPrice"));
                    products.add(product);
                }


                req.setAttribute("Products", products);
                req.setAttribute("Code", 0);
                req.setAttribute("Message", ProductMessage[0]);
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage());
            }
        } else {
            req.setAttribute("Code", 1);
            req.setAttribute("Message", ProductMessage[1]);
        }

        req.getRequestDispatcher("").forward(req, resp);
    }
}
