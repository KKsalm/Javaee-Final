package Controller.Api.SaleRecord;

import Database.SaleRecordDataController;
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

@WebServlet(name = "DeleteSaleRecordServlet")
public class DeleteSaleRecordServlet extends HttpServlet {

    private final String[] Message = {"Delete Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(DeleteSaleRecordServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");


        if (currentUser != null) {
            try {

                SaleRecordDataController saleRecordDataController = new SaleRecordDataController();
                saleRecordDataController.delete(Integer.parseInt(request.getParameter("saleRecordID")));
                saleRecordDataController.closeConnection();

                session.setAttribute("Code", 0);
                session.setAttribute("Message", Message[0]);
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage());
            } catch (NamingException namingException) {
                logger.error(namingException.getMessage());
            }
        } else {
            session.setAttribute("Code", 1);
            session.setAttribute("Message", Message[1]);
        }


        response.sendRedirect("/orderManage.jsp");
    }

}
