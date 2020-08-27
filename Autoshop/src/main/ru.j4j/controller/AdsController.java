package controller;

import data.AutoDB;
import model.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/ads")
public class AdsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Car> cars = null;
        String sort = req.getParameter("sort");

        if (sort != null) {
            switch (sort) {
                case "photo":
                    cars = AutoDB.getInst().getAll(Car.class).stream().filter(c ->
                            c.getImage() != 0).collect(Collectors.toList());
                    break;
                case "brand":
                    int brandId = Utils.getOr(req.getParameter("brandId"));
                    cars = AutoDB.getInst().getAll(Car.class).stream().filter(c ->
                            c.getModel().getBrand().getId() == brandId)
                            .collect(Collectors.toList());
                    break;
                case "day":
                    cars = AutoDB.getInst().getAll(Car.class).stream().filter(c ->
                            c.getDate().getDay() == new Date().getDay())
                            .collect(Collectors.toList());
                    break;
            }
        } else {
            cars = AutoDB.getInst().getAll(Car.class);
        }

        req.setAttribute("cars", cars);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}