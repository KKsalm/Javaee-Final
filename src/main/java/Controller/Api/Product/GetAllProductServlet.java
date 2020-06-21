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
import java.util.List;

@WebServlet(name = "GetAllProductServlet")
public class GetAllProductServlet extends HttpServlet {
    private static final String[] Message = {"Get Info Success", "Without Permission"};
    private final Logger logger = LogManager.getLogger(getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        User currentUser = (User) request.getAttribute("CurrentUser");

        if (currentUser != null) {
            try {
                List<Product> products = null;

                ProductDataController productDataController = new ProductDataController();
                products = productDataController.queryAll();

                for (Product product: products) {
                    System.out.println(product.toString());
                }

                request.setAttribute("Products", products);
                request.setAttribute("Code", 0);
                request.setAttribute("Message", Message[0]);
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage());
            } catch (NamingException namingException) {
                logger.error(namingException.getMessage());
            } catch (IllegalAccessException illegalAccessException) {
                logger.error(illegalAccessException.getMessage());
            } catch (InstantiationException instantiationException) {
                logger.error(instantiationException.getMessage());
            }
        } else {
            request.setAttribute("Code", 1);
            request.setAttribute("Message", Message[1]);
        }

        // req.getRequestDispatcher("").forward(req, resp);
    }
}
