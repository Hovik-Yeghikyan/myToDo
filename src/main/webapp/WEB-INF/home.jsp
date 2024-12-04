
<%@ page import="java.util.List" %>
<%@ page import="com.vector.mytodo.model.User" %>
<%@ page import="com.vector.mytodo.model.ToDo" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html lang="en">
<head>
    <title>TODO</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,600;0,700;0,800;1,300;1,400;1,600;1,700;1,800&amp;display=swap">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootlint/1.1.0/bootlint.min.js"></script>

    <link rel="stylesheet" href="css/style.css">
    <script src="js/home.js"></script>
</head>
<body>


<% if (session.getAttribute("user") != null) {
    User user = (User) session.getAttribute("user");
%>

Welcome <%=user.getName() + " " + user.getSurname()%>
<%if (user.getImgName() != null) {%>
<img src="/getImage?imageName=<%=user.getImgName()%>" width="100"></td>
<%} else {%>
<span>no image</span>
<%}%>
!!! <a href="/logout">Logout</a>
<%
    }
%>
<div class="container m-5 p-2 rounded mx-auto bg-light shadow">
    <!-- App title section -->
    <div class="row m-1 p-4">
        <div class="col">
            <div class="p-1 h1 text-primary text-center mx-auto display-inline-block">
                <i class="fa fa-check bg-primary text-white rounded p-2"></i>
                <u>My Todo-s</u>
            </div>
        </div>
    </div>
    <%
        List<ToDo> toDoList = (List<ToDo>) request.getAttribute("toDos");

    %>
    <!-- Create todo section -->
    <div class="row m-1 p-3">
        <div class="col col-11 mx-auto">
            <form method="post" action="/addToDo">
                <div class="row bg-white rounded shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center">

                    <div class="col">

                        <input class="form-control form-control-lg border-0 add-todo-input bg-transparent rounded"
                               type="text" name="title" placeholder="Add new ..">
                    </div>

                    <div class="col-auto m-0 px-2 d-flex align-items-center">
                        <label class="text-secondary my-2 p-0 px-1 view-opt-label due-date-label d-none">Due date not
                            set</label>
                        <i class="fa fa-calendar my-2 px-1 text-primary btn due-date-button" data-toggle="tooltip"
                           data-placement="bottom" title="Set a Due date"></i>
                        <i class="fa fa-calendar-times-o my-2 px-1 text-danger btn clear-due-date-button d-none"
                           data-toggle="tooltip" data-placement="bottom" title="Clear Due date"></i>
                    </div>
                    <div class="col-auto px-0 mx-0 mr-2">
                        <button type="submit" class="btn btn-primary">Add</button>
                    </div>

                </div>
            </form>
        </div>
    </div>
    <div class="p-2 mx-4 border-black-25 border-bottom"></div>
    <tr>
            <%
        if (!toDoList.isEmpty()){
            for (ToDo toDo : toDoList) {%>


            <%
       User user =(User) request.getSession().getAttribute("user");
        if (user.equals(toDo.getUser())) {%>
    <tr>

    <table  width="100%">
        <tr>
            <th>Title</th>
            <th>Created Date</th>
            <%if (toDo.getFinishDate()!=null){%>
            <th>Finish Date</th>
            <%}%>
            <th>Status</th>
            <th>Created by</th>
            <%--            <th>Edit</th>--%>
            <%--            <th>Update</th>--%>
            <%--            <th>Delete</th>--%>
        </tr>


            <td><%=toDo.getTitle()%>
            </td>
            <td><%=toDo.getCreatedDate()%>
            </td>
        <%if (toDo.getFinishDate()!=null){%>
            <td><%=toDo.getFinishDate()%>
            </td>
        <%}%>

            <td><%=toDo.getStatus()%>
            </td>
            <td><%=toDo.getUser().getName() + " " + toDo.getUser().getSurname()%>
            </td>

            <td><a href="/editToDos?id=<%=toDo.getId()%>"><button class="btn btn-primary">Edit</button> </a></td>
            <td><a href="/updateToDos?id=<%=toDo.getId()%>"><button class="btn btn-primary">Update</button> </a></td>
            <td><a href="/deleteToDos?id=<%=toDo.getId()%>"><button class="btn btn-primary">Delete</button> </a></td>
        </tr>

        <% }%>
        <% }%>
        <% }%>
    </table>

</div>
</div>
</body>
</html>