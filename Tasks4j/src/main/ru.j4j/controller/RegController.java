package controller;

import data.TaskDB;
import model.Role;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reg")
public class RegController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("allRole", TaskDB.getInstance().getAllRoles());
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String pas = req.getParameter("password");
        Role role = TaskDB.getInstance().getRoleById(req.getParameter("role"));

        if (TaskDB.getInstance().findUserByUsername(username) == null) {
            TaskDB.getInstance().addUser(new User(username, pas, role));
            req.setAttribute("authError", "Регистрация прошла успешно.");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            req.setAttribute("allRole", TaskDB.getInstance().getAllRoles());
            req.setAttribute("authError", "Email занят");
            req.getRequestDispatcher("reg.jsp").forward(req, resp);
        }
    }
}
