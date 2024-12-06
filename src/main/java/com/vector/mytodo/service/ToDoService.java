package com.vector.mytodo.service;

import com.vector.mytodo.db.DBConnectionProvider;
import com.vector.mytodo.model.Status;
import com.vector.mytodo.model.ToDo;
import com.vector.mytodo.util.DateUtil;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ToDoService {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();
    private UserService userService = new UserService();

    public List<ToDo> getAll() {
        String sql = "SELECT * FROM to_do";
        List<ToDo> toDos = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                toDos.add(ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .createdDate(resultSet.getDate("created_date"))
                        .finishDate(resultSet.getDate("finish_date"))
                        .user(userService.getUserById(resultSet.getInt("user_id")))
                        .status(Status.valueOf(resultSet.getString("status")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDos;
    }

    public ToDo getToDoById(int id) {
        String sql = "SELECT * FROM to_do WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                return ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .createdDate(DateUtil.fromSqlStringToDateTime(resultSet.getString("created_date")))
                        .user(userService.getUserById(resultSet.getInt("user_id")))
                        .status(Status.valueOf(resultSet.getString("status")))
                        .build();
            }
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ToDo>  getToDoByUserId(int id) {
        String sql = "SELECT * FROM to_do WHERE user_id=" + id;
        List<ToDo> toDos = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                toDos.add(ToDo.builder()
                        .id(resultSet.getInt("id"))
                        .title(resultSet.getString("title"))
                        .createdDate(resultSet.getDate("created_date"))
                        .finishDate(resultSet.getDate("finish_date"))
                        .user(userService.getUserById(resultSet.getInt("user_id")))
                        .status(Status.valueOf(resultSet.getString("status")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toDos;
    }

    public void add(ToDo toDos) {
        String sql = "INSERT INTO to_do(title,created_date,user_id,status) VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, toDos.getTitle());
            preparedStatement.setString(2, DateUtil.fromDateToSqlDateTimeString(toDos.getCreatedDate()));
            preparedStatement.setInt(3, toDos.getUser().getId());
            preparedStatement.setString(4,toDos.getStatus().name());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int id = generatedKeys.getInt(1);
                toDos.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteToDo(int id) {
        String sql = "DELETE FROM to_do WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(ToDo toDos) {
        String sql = "UPDATE to_do SET  finish_date = ?, status = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, DateUtil.fromDateToSqlDateTimeString(toDos.getFinishDate()));
            preparedStatement.setString(2, toDos.getStatus().name());
            preparedStatement.setInt(3,toDos.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateTitle(ToDo toDos) {
        String sql = "UPDATE to_do SET  title = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,toDos.getTitle());
            preparedStatement.setInt(2,toDos.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
