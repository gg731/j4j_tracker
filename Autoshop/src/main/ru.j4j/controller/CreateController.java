package controller;

import data.AutoDB;
import model.Brand;
import model.Car;
import model.Driver;
import model.Model;
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
        System.out.println(name + " " + brandId + " " + model);
        int price = Integer.valueOf(req.getParameter("price"));
        System.out.println(price);
        Brand brand1 = AutoDB.getInst().findById(Brand.class, Integer.valueOf(brandId));
        Model model1 = new Model(model, brand1);
        Car car1 = new Car(name, model1, price);
        int id = AutoDB.getInst().add(car1);
        String idx = String.valueOf(id);
        Driver driver = (Driver) req.getSession().getAttribute("driver");
        driver.addCar(AutoDB.getInst().findById(Car.class, id));

        AutoDB.getInst().update(driver);

//        DiskFileItemFactory factory = new DiskFileItemFactory();
//        ServletContext servletContext = this.getServletConfig().getServletContext();
//        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
//        factory.setRepository(repository);
//        ServletFileUpload upload = new ServletFileUpload(factory);
//
//        try {
//            List<FileItem> items = upload.parseRequest(req);
//            File folder = new File("images");
//
//            if (!folder.exists()) {
//                folder.mkdir();
//            }
//            for (FileItem item : items) {
//                if (!item.isFormField()) {
//                    if (idx == null) {
//                        idx = item.getName();
//                    }
//                    File file = new File(folder + File.separator + idx);
//                    try (FileOutputStream out = new FileOutputStream(file)) {
//                        out.write(item.getInputStream().readAllBytes());
//                    }
//                }
//            }
//        } catch (FileUploadException e) {
//            e.printStackTrace();
//        }

        resp.sendRedirect(req.getContextPath() + "/driver");
    }
}
