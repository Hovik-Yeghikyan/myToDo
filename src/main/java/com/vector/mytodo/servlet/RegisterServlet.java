package com.vector.mytodo.servlet;

import com.vector.mytodo.model.User;
import com.vector.mytodo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
@MultipartConfig(maxFileSize = 1024 * 1024 * 5,//5mb
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 1)
public class RegisterServlet extends HttpServlet {

    private UserService userService = new UserService();
    private final String IMAGE_UPLOAD_FOLDER = "C:\\Users\\hovoe\\IdeaProjects\\JavaEE\\myToDo\\images\\";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Part img = req.getPart("img");

        HttpSession session = req.getSession();
        StringBuilder msgBuilder = new StringBuilder();
        if (name == null || name.trim().isEmpty()) {
            msgBuilder.append("Name is required");
            msgBuilder.append("<br>");
        }
        if (surname == null || surname.trim().isEmpty()) {
            msgBuilder.append("Surname is required");
            msgBuilder.append("<br>");
        }
        if (email == null || email.trim().isEmpty()) {
            msgBuilder.append("Email is required");
            msgBuilder.append("<br>");
        }
        if (password == null || password.isEmpty() || password.trim().length() < 6) {
            msgBuilder.append("Password is required or password`s length less then 6");
            msgBuilder.append("<br>");

        }
        String fileName = null;
        if (img != null && img.getSize() > 0) {
            fileName = System.currentTimeMillis() + "_" + img.getSubmittedFileName();
            img.write(IMAGE_UPLOAD_FOLDER + fileName);
        }

        if (!msgBuilder.isEmpty()) {
            session.setAttribute("msg", msgBuilder.toString());
        } else if (userService.getUserByEmail(email) != null) {
            msgBuilder.append("Email Already Exists");
            msgBuilder.append("<br>");
        } else {
            User user = User.builder()
                    .name(name)
                    .surname(surname)
                    .email(email)
                    .password(password)
                    .imgName(fileName)
                    .build();
            userService.add(user);
            session.setAttribute("msg", "User Registered");
        }
        resp.sendRedirect("/register");
    }
}
