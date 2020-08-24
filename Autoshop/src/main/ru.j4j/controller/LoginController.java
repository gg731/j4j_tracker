package controller;

import data.AutoDB;
import model.Brand;
import model.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String login = req.getParameter("login");
        String pas = req.getParameter("password");

        Driver driver = AutoDB.getInst().findDriverByLogin(login);

        if (driver == null || !driver.getPas().equals(pas)) {
            req.setAttribute("authError", "Неккоректные данные");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("driver", driver);
            req.getSession().setAttribute("driverID", driver.getId());
            req.getSession().setAttribute("brands", AutoDB.getInst().getAll(Brand.class));
            resp.sendRedirect("/ads");
        }
    }
}
