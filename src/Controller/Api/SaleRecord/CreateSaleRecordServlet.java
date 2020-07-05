package Controller.Api.SaleRecord;

import Database.ProductDataController;
import Database.SaleRecordDataController;
import Model.Product;
import Model.SaleRecord;
import Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "CreateSaleRecordServlet")
public class CreateSaleRecordServlet extends HttpServlet {

    private final String[] Message = {"Create Successfully", "Without Permission", "SaleRecord Already Exist"};
    private final Logger logger = LogManager.getLogger(CreateSaleRecordServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            if (currentUser != null) {
                if (currentUser.getStoreID() == null) {
                    session.setAttribute("Code", 1);
                    session.setAttribute("Message", Message[1]);
                } else {
                    ProductDataController productDataController = new ProductDataController();
                    Product product = productDataController.queryByID(Integer.parseInt(request.getParameter("productID")));
                    productDataController.closeConnection();
                    date = sf.parse(sf.format(date));

                    SaleRecord saleRecord = new SaleRecord(product.getProductID(), Integer.parseInt(request.getParameter("productNum")),
                            product.getProductPrice() * Integer.parseInt(request.getParameter("productNum")), currentUser.getStoreID(), date);

                    SaleRecordDataController saleRecordDataController = new SaleRecordDataController();
                    saleRecordDataController.add(saleRecord);
                    saleRecordDataController.closeConnection();

                    session.setAttribute("Code", 0);
                    session.setAttribute("Message", Message[0]);
                }

            } else {
                session.setAttribute("Code", 1);
                session.setAttribute("Message", Message[1]);
            }
        } catch (SQLException sqlException) {
            if (sqlException.getErrorCode() == 1062) {
                session.setAttribute("Code", 2);
                session.setAttribute("Message", Message[2]);
            }
            logger.error(sqlException.getMessage());
        } catch (NamingException namingException) {
            logger.error(namingException.getMessage());
        } catch (IllegalAccessException illegalAccessException) {
            logger.error(illegalAccessException.getMessage());
        } catch (ParseException parseException) {
            logger.error(parseException.getMessage());
        } catch (InstantiationException instantiationException) {
            logger.error(instantiationException.getMessage());
        }

        response.sendRedirect("/orderManage.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
