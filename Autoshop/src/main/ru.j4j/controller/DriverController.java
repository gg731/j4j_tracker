package controller;

import data.AutoDB;
import model.Car;
import model.Driver;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/driver")
public class DriverController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if (id != null) {
            int status = Integer.valueOf(req.getParameter("status"));
            int rsl = status == 1 ? 0 : 1;
            Car car = AutoDB.getInst().findById(Car.class, Integer.valueOf(id));
            car.setStatus(Integer.valueOf(rsl));
            AutoDB.getInst().update(car);
        }

        req.getSession().setAttribute("driver", AutoDB.getInst()
                .findById(Driver.class, (Integer) req.getSession().getAttribute("driverID")));

        req.getRequestDispatcher("driver.jsp").forward(req, resp);
    }
}
