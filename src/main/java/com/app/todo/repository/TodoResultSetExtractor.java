package com.app.todo.repository;

import com.app.todo.model.Todo;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
// import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@Repository
public class TodoResultSetExtractor implements ResultSetExtractor<List<Todo>> {
    @Override
    public List<Todo> extractData(ResultSet rs) throws SQLException, DataAccessException {
        List<Todo> todoList = new ArrayList<>();
        while (rs.next()) {
            Todo todo = new Todo();
            todo.setId(rs.getLong("id"));
            todo.setTask(rs.getString("task_name"));
            todo.setContext(rs.getString("task_context"));
            todo.setCreateDate(rs.getObject("create_date", LocalDateTime.class));
            todo.setDeadline(rs.getObject("deadline", LocalDateTime.class));

            todoList.add(todo);
        }
        return todoList;
    }
}
