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
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "UpdateSaleRecordServlet")
public class UpdateSaleRecordServlet extends HttpServlet {

    private final String[] Message = {"Update Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(UpdateSaleRecordServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        if (currentUser != null) {
            try {

                ProductDataController productDataController = new ProductDataController();
                Product product = productDataController.queryByID(Integer.parseInt(request.getParameter("productID")));
                productDataController.closeConnection();

                float totalPrice = Integer.parseInt(request.getParameter("productNum")) * product.getProductPrice();

                SaleRecord saleRecord = new SaleRecord(Integer.parseInt(request.getParameter("saleRecordID")), product.getProductID(), Integer.parseInt(request.getParameter("productNum")),
                        totalPrice, currentUser.getStoreID() ,sf.parse(request.getParameter("date")));
                SaleRecordDataController saleRecordDataController = new SaleRecordDataController();
                saleRecordDataController.update(saleRecord);
                saleRecordDataController.closeConnection();

                session.setAttribute("Code", 0);
                session.setAttribute("Message", Message[0]);
            } catch (SQLException sqlException) {
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
        } else {
            session.setAttribute("Code", 1);
            session.setAttribute("Message", Message[1]);
        }

        response.sendRedirect("/orderManage.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
