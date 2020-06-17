package main.java.Controller.Api.User;

import main.java.Controller.Api.Product.CreateProductController;
import main.java.Database.UserDataController;
import main.java.Model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetUsersController extends HttpServlet {
    private static final String[] GetUsersMessage = {"Get Users Successfully", "Without Permission"};
    private final Logger logger = LogManager.getLogger(CreateProductController.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");

        if (user != null) {
            switch (user.getPosition()) {
                case "staff":
                    req.setAttribute("Code", 1);
                    req.setAttribute("Message", GetUsersMessage[1]);
                    break;
                default:
                    try {
                        List<User> users = new ArrayList<>();

                        ResultSet resultSet = UserDataController.getAllUsers();

                        while (resultSet.next()) {
                            User user1 = new User(resultSet.getInt("userID"), resultSet.getInt("workNumber"), resultSet.getString("name"),
                                    resultSet.getString("position"), resultSet.getInt("storeID"), resultSet.getFloat("monthWorkTime"), resultSet.getFloat("monthWorkSalary"));
                            users.add(user1);
                        }
                        req.setAttribute("Users", users);
                        req.setAttribute("Code", 0);
                        req.setAttribute("Message", GetUsersMessage[0]);
                    } catch (SQLException sqlException) {
                        logger.error(sqlException.getMessage());
                    }
                    break;

            }
        }

        req.getRequestDispatcher("").forward(req, resp);
    }
}
