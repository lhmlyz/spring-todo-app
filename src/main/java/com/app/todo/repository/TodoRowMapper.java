package com.app.todo.repository;

import com.app.todo.model.Todo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Repository
public class TodoRowMapper implements RowMapper<Todo> {

    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Todo todo = new Todo();
        todo.setId(rs.getLong("id"));
        todo.setTask(rs.getString("task_name"));
        todo.setContext(rs.getString("task_context"));
        todo.setCreateDate(rs.getObject("create_date", LocalDateTime.class));
        todo.setDeadline(rs.getObject("deadline", LocalDateTime.class));
        return todo;
    }
}
