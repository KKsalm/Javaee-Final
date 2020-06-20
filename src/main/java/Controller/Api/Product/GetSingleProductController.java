package main.java.Controller.Api.Product;

import main.java.Database.ProductDataController;
import main.java.Database.UserDataController;
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

public class GetSingleProductController extends HttpServlet {
    private static final String[] Message = {"Get Product Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(GetSingleProductController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser != null) {
            try {
                Product product = null;
                ProductDataController productDataController = new ProductDataController();
                product = productDataController.queryByID(Integer.parseInt(req.getParameter("queryID")));

                req.setAttribute("ProductInfo", product);
                req.setAttribute("Code", 0);
                req.setAttribute("Message", Message[0]);
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
            req.setAttribute("Code", 1);
            req.setAttribute("Message", Message[1]);
        }
    }
}
