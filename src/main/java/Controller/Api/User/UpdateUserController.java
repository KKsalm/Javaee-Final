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

public class UpdateUserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            User user = new User(Integer.parseInt(req.getParameter("userID")), new Integer(req.getParameter("workNumber")), req.getParameter("name"), req.getParameter("position"),
                    new Integer(req.getParameter("storeID")), Float.parseFloat(req.getParameter("monthWorkTime")), Float.parseFloat(req.getParameter("monthSalary")), req.getParameter("username"),
                    req.getParameter("password"));
            UserDataController userDataController = new UserDataController();
            userDataController.update(user);

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
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
