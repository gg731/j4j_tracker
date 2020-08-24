package controller;

import data.AutoDB;
import model.Car;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@WebServlet("/upimg")
public class ImgController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.valueOf(req.getParameter("idCar"));
        Car car = AutoDB.getInst().findById(Car.class, id);
        req.setAttribute("car", car);

        req.getRequestDispatcher("/upPhoto.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        String photoId = req.getParameter("photoId");

        try {
            List<FileItem> items = upload.parseRequest(req);
            File folder = new File("images");

            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    if (photoId == null) {
                        photoId = item.getName();
                    }
                    File file = new File(folder + File.separator + photoId);
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        if (photoId == req.getParameter("photoId")) {
            resp.sendRedirect(req.getContextPath() + "/driver");
        } else {
            doGet(req, resp);
        }
    }
}

