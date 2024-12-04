package com.vector.mytodo.servlet;

import com.vector.mytodo.model.ToDo;
import com.vector.mytodo.service.ToDoService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/updateToDos")
public class UpdateToDoServlet extends HttpServlet {

    private ToDoService toDoService = new ToDoService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        ToDo toDos = toDoService.getToDoById(id);
        req.setAttribute("toDos", toDos);
        req.getRequestDispatcher("/WEB-INF/updateToDos.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String title = req.getParameter("title");
        toDoService.updateTitle(ToDo.builder()
                .title(title)
                .id(id)
                .build());
        resp.sendRedirect("/home");
    }
}
