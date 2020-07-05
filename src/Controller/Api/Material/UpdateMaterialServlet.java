package Controller.Api.Material;

import Database.MaterialDataController;
import Model.Material;
import Model.User;
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

@WebServlet(name = "UpdateMaterialServlet")
public class UpdateMaterialServlet extends HttpServlet {
    private static final String[] Message = {"Update Successfully", "Without Permission", "Material Name Already Exist"};
    private final Logger logger = LogManager.getLogger(getClass());

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("CurrentUser");

        if (currentUser != null) {
            try {
                Material material = new Material(Integer.parseInt(request.getParameter("materialID")), request.getParameter("materialName"), Float.parseFloat(request.getParameter("price")));

                MaterialDataController materialDataController = new MaterialDataController();
                materialDataController.update(material);
                materialDataController.closeConnection();

                session.setAttribute("Code", 0);
                session.setAttribute("Message", Message[0]);
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
            }
        } else {
            session.setAttribute("Code", 1);
            session.setAttribute("Message", Message[1]);
        }

        response.sendRedirect("/getCommodity.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
