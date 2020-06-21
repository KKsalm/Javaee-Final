package main.java.Controller.Api.Material;

import main.java.Database.MaterialDataController;
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

@WebServlet(name = "DeleteMaterialServlet")
public class DeleteMaterialServlet extends HttpServlet {
    private static final String[] Message = {"Delete Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        if (currentUser != null) {
            try {
                MaterialDataController materialDataController = new MaterialDataController();
                materialDataController.delete(Integer.parseInt(request.getParameter("materialID")));

                request.setAttribute("Code", 0);
                request.setAttribute("Message", Message[0]);
            } catch (SQLException sqlException) {
                logger.error(sqlException.getMessage());
            } catch (NamingException namingException) {
                logger.error(namingException.getMessage());
            }
        } else {
            request.setAttribute("Code", 1);
            request.setAttribute("Message", Message[1]);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
