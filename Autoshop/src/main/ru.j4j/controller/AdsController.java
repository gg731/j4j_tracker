package controller;

import data.AutoDB;
import model.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ads")
public class AdsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        List<Car> cars = AutoDB.getInst().getAll(Car.class);
        String sort = req.getParameter("sort");

        if (sort != null) {
            switch (sort) {
                case "photo":
                    cars = AutoDB.getInst().sortByPhoto();
                    break;
                case "brand":
                    int brandId = Utils.getOr(req.getParameter("brandId"));
                    cars = AutoDB.getInst().sortByBrand(brandId);
                    break;
                case "day":
                    cars = AutoDB.getInst().sortByDate();
                    break;
            }
        }

        req.setAttribute("cars", cars);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
}