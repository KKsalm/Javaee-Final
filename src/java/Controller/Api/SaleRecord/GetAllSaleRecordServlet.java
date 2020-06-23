package Controller.Api.SaleRecord;

import Database.SaleRecordDataController;
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
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "GetAllSaleRecordServlet")
public class GetAllSaleRecordServlet extends HttpServlet {

    private final String[] Message = {"Get SaleRecord Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(GetAllSaleRecordServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("currentUser");

        if (currentUser != null) {
            try {
                List<SaleRecord> saleRecords = null;
                SaleRecordDataController saleRecordDataController = new SaleRecordDataController();
                saleRecords = saleRecordDataController.queryAll();

                for (SaleRecord saleRecord : saleRecords) {
                    System.out.println(saleRecord.toString());
                }

                request.setAttribute("SaleRecords", saleRecords);
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

        //request.getRequestDispatcher("").forward(request, response);
    }

}
