package com.vector.mytodo.servlet;

import com.vector.mytodo.model.Status;
import com.vector.mytodo.model.ToDo;
import com.vector.mytodo.model.User;
import com.vector.mytodo.service.ToDoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/addToDo")

public class AddToDoServlet extends HttpServlet {

    private ToDoService toDoService = new ToDoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String title = req.getParameter("title");
        toDoService.add(ToDo.builder()
                .title(title)
                .createdDate(new Date())
                .user(user)
                .status(Status.NEW)
                .build());
        resp.sendRedirect("/home");

    }

}