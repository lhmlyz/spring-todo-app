package com.app.todo.repository.jdbc;

import com.app.todo.model.Todo;
// import org.springframework.jdbc.core.JdbcTemplate;
// import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepositoryImpl implements TodoRepository {


//    private JdbcTemplate jdbcTemplate;
//    private TodoResultSetExtractor resultSetExtractor;

    private TodoRowMapper rowMapper;
    private NamedParameterJdbcTemplate jdbcTemplate;


    public TodoRepositoryImpl(NamedParameterJdbcTemplate jdbcTemplate, TodoRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public List<Todo> getTodoList() {
        String sql = "select id, task_name, task_context, create_date, deadline " + "from todo_data";
        return jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public boolean isTaskNameExist(String name) {
        String sql = "select count(id) from todo_data where task_name =:name";
        MapSqlParameterSource params = new MapSqlParameterSource("name", name);
        int count = jdbcTemplate.queryForObject(sql, params, Integer.class);
        return count > 0;
    }

    @Override
    public Optional<Todo> getTodoById(long id) {
//        String sql = "select id, task_name, task_context, create_date, deadline " + "from todo_data where id=?";
        String sql = "select id, task_name, task_context, create_date, deadline " + "from todo_data where id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        Optional<Todo> optionalTodo = Optional.empty();
        //List<Todo> todoList = jdbcTemplate.query(sql, rowMapper, id);
        List<Todo> todoList = jdbcTemplate.query(sql, params, rowMapper);
        if (!todoList.isEmpty()) {
            optionalTodo = Optional.of(todoList.get(0));
        }

        return optionalTodo;
    }

    @Override
    public Todo addNewTodo(Todo todo) {
//        String sql = "insert into todo_data(task_name, task_context, create_date, deadline) values (?, ?, ?, ?)";
        String sql = "insert into todo_data(task_name, task_context, create_date, deadline)" +
                " values (:task, :context, :create_date, :deadline)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource("task", todo.getTask())
                .addValue("context", todo.getContext())
                .addValue("create_date", LocalDateTime.now(), Types.TIMESTAMP)
                .addValue("deadline", todo.getDeadline(), Types.TIMESTAMP);
        int count = jdbcTemplate.update(sql, params, keyHolder, new String[]{"id"});


        if (count == 1) {
            todo.setId(keyHolder.getKey().longValue());
        }
        return todo;
    }

    @Override
    public Todo updateTodo(Todo todo) {
//        String sql = "update todo_data set task_name=?, task_context=?, deadline=? where id=?";
        String sql = "update todo_data set task_name=:task, task_context=:context, deadline=:deadline where id=:id";
//        Object[] data = new Object[]{todo.getTask(), todo.getContext(), todo.getDeadline(), todo.getId()};
        MapSqlParameterSource params = new MapSqlParameterSource("task", todo.getTask())
                .addValue("context", todo.getContext())
                .addValue("deadline", todo.getDeadline())
                .addValue("id", todo.getId());
        jdbcTemplate.update(sql, params);
        System.out.println(todo);
        return todo;
    }

    @Override
    public void deleteTodo(long id) {
//        String sql = "delete from todo_data where id=?";
        String sql = "delete from todo_data where id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        jdbcTemplate.update(sql, params);
    }
}
