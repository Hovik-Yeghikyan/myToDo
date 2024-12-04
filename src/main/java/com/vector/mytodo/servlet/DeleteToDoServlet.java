package com.vector.mytodo.servlet;

import com.vector.mytodo.service.ToDoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteToDos")
public class DeleteToDoServlet extends HttpServlet {

    private ToDoService toDoService = new ToDoService();


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        toDoService.deleteToDo(id);
        resp.sendRedirect("/home");
    }
}