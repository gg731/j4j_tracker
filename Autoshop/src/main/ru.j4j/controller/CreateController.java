package controller;

import data.AutoDB;
import model.Brand;
import model.Car;
import model.Driver;
import model.Model;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/create")
public class CreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        String brandId = req.getParameter("brand");
        String model = req.getParameter("model");

        int price = Utils.getOr(req.getParameter("price"));

        Brand brand1 = AutoDB.getInst().findById(Brand.class, Utils.getOr(brandId));
        Model model1 = new Model(model, brand1);
        Car car1 = new Car(name, model1, price, new Date());
        int id = 0;
        try {
            id = AutoDB.getInst().add(car1);
        } catch (Exception e) {
            req.setAttribute("error", "Уже существует.");
            req.getRequestDispatcher("create.jsp").forward(req, resp);
        }
        Driver driver = (Driver) req.getSession().getAttribute("driver");
        driver.addCar(AutoDB.getInst().findById(Car.class, id));

        AutoDB.getInst().update(driver);

        resp.sendRedirect(req.getContextPath() + "/driver");
    }
}
