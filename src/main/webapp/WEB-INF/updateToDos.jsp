<%@ page import="com.vector.mytodo.model.ToDo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit ToDO</title>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,600;0,700;0,800;1,300;1,400;1,600;1,700;1,800&amp;display=swap">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet">
</head>
<body>
<%ToDo toDo = (ToDo) request.getAttribute("toDos");%>
Edit ToDO <br>
<form method="post" action="/updateToDos">
    <table width="70%">
        <tr>
            <th>Title</th>
            <th>Created Date</th>
            <%if (toDo.getFinishDate()!=null){%>
            <th>Finish Date</th>
            <%}%>
            <th>Status</th>
            <th>Created by</th>
        </tr>
        <input type="hidden" name="id" value="<%=toDo.getId()%>">
        <td><input type="text" name="title" value="<%=toDo.getTitle()%>">
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
        <td>
            <button type="submit" class="btn btn-primary">Update</button>
        </td>
    </table>
</form>
</tr>
</body>
</html>