package controller;

import com.google.gson.Gson;
import data.TaskDB;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tasks")
public class TableController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        User user = (User) req.getSession().getAttribute("user");

        String tableJson = new Gson().toJson(TaskDB.getInstance()
                .findAllForUserId(user.getId()));

        resp.setContentType("JSON");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(tableJson);
    }
}
