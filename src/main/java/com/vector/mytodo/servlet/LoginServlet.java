package com.vector.mytodo.servlet;

import com.vector.mytodo.model.User;
import com.vector.mytodo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userService.getUserByEmailAndPassword(email, password);
        if (user != null) {
            req.getSession().setAttribute("user", user);
          resp.sendRedirect("/home");
        }else {
            req.getSession().setAttribute("msg","Username or password is incorrect");
          resp.sendRedirect("/login");
        }
    }
}
