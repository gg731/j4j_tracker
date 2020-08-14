package controller;

import data.Task;
import data.TaskDB;
import model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/done")
public class DoneController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int id = Integer.valueOf(req.getParameter("id"));
        int done = Integer.valueOf(req.getParameter("done"));

        TaskDB.getInstance().doneTaskById(Integer.valueOf(id), done);
        resp.sendRedirect(req.getContextPath() + "/tasks");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TaskDB.getInstance().addTask(
                new Item(
                        req.getParameter("task"),
                        req.getParameter("about")
                ));

        resp.sendRedirect(req.getContextPath() + "/tasks");
    }
}
