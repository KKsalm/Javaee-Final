package Controller.Api.Product;

import Database.ProductDataController;
import Model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class CreateProductController extends HttpServlet {
    private static final String[] CreateProductMessage = {"Create Products Successfully", "Empty Request", "Product Name Already Exist"};
    private final Logger logger = LogManager.getLogger(CreateProductController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if (req.getParameter("productName") != null && req.getParameter("productPrice") != null) {
            Product product = new Product(req.getParameter("productName"), Integer.parseInt(req.getParameter("productPrice")));

            try {
                ProductDataController.createProduct(product);

                req.setAttribute("Code", 0);
                req.setAttribute("Message", CreateProductMessage[0]);
            } catch (SQLException sqlException) {
                if (sqlException.getErrorCode() == 1062) {
                    req.setAttribute("Code", 2);
                    req.setAttribute("Message", CreateProductMessage[2]);
                }
                logger.error(sqlException.getMessage());
            }
        } else {
            req.setAttribute("Code", 1);
            req.setAttribute("Message", CreateProductMessage[1]);
        }

        req.getRequestDispatcher("").forward(req, resp);
    }
}
