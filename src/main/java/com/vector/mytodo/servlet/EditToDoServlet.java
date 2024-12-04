package com.vector.mytodo.servlet;


import com.vector.mytodo.model.Status;
import com.vector.mytodo.model.ToDo;
import com.vector.mytodo.service.ToDoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(urlPatterns = "/editToDos")

public class EditToDoServlet extends HttpServlet {
    private ToDoService toDoService = new ToDoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        toDoService.update(ToDo.builder()
                .finishDate(new Date())
                .status((Status.DONE))
                .id(id)
                .build());
        resp.sendRedirect("/home");

    }

}