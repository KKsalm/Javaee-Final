package main.java.Controller.Api.User;

import main.java.Database.UserDataController;
import main.java.Model.User;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteUserController extends HttpServlet {
    // 未添加权限限制 已经调通
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = null;
            UserDataController userDataController = new UserDataController();
            userDataController.delete(Integer.parseInt(req.getParameter("deleteID")));

             System.out.println(req.getParameter("deleteID"));
            // System.out.println(user.toString());

//            req.setAttribute("UserInfo", user);
//            req.setAttribute("Code", 0);
            // req.setAttribute("Message", GetSingleUserMessage[0]);
        } catch (SQLException sqlException) {
            // logger.error(sqlException.getMessage());
        } catch (NamingException namingException) {
            namingException.printStackTrace();
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        }
    }
}
