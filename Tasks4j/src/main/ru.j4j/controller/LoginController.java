package controller;

import data.TaskDB;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String pas = req.getParameter("password");

        User user = TaskDB.getInstance().findUserByUsername(username);

        if (TaskDB.getInstance().findUserByUsername(username) != null
                && user.getPassword().equals(pas)) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect(req.getContextPath() + "/tasks");
        } else {
            req.setAttribute("authError", "Не верная почта или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
