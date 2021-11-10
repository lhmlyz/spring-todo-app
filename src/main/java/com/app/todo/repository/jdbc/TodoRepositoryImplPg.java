package com.app.todo.repository.jdbc;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.app.todo.model.Todo;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

// @Repository
public class TodoRepositoryImplPg implements TodoRepository {
    NamedParameterJdbcTemplate jdbcTemplate;
    TodoRowMapper rowMapper;
    SimpleJdbcCall callGetTodoList;
    SimpleJdbcCall callGetTodoById;
    SimpleJdbcCall callDeleteTodoById;

    public TodoRepositoryImplPg(NamedParameterJdbcTemplate jdbcTemplate, TodoRowMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;

        jdbcTemplate.getJdbcTemplate().setResultsMapCaseInsensitive(true);

        this.callGetTodoList = new SimpleJdbcCall(jdbcTemplate.getJdbcTemplate()).withFunctionName("get_todo_list")
                .returningResultSet("list", this.rowMapper);

        this.callGetTodoById = new SimpleJdbcCall(jdbcTemplate.getJdbcTemplate()).withFunctionName("get_todo_by_id")
                .returningResultSet("data", this.rowMapper);

        // this.callDeleteTodoById = new SimpleJdbcCall(jdbcTemplate.getJdbcTemplate())
        //         .withProcedureName("delete_todo_by_id").declareParameters(new SqlParameter("todo_id", Types.INTEGER));

    }

    @Override
    public List<Todo> getTodoList() {
        Map<String, Object> params = callGetTodoList.execute();
        List<Todo> todoList = (List<Todo>) params.get("list");
        return todoList;
    }

    @Override
    public Optional<Todo> getTodoById(long id) {
        Optional<Todo> optionalTodo = Optional.empty();
        Map<String, Object> params = callGetTodoById.execute(id);
        List<Todo> todo = (List<Todo>) params.get("data");
        if (!todo.isEmpty()) {
            optionalTodo = Optional.of(todo.get(0));
        }
        return optionalTodo;
    }

    @Override
    public boolean isTaskNameExist(String mail) {
        return false;
    }

    @Override
    public Todo updateTodo(Todo todo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Todo addNewTodo(Todo todo) {
        // TODO Auto-generated method stub
        return todo;
    }

    @Override
    public void deleteTodo(long id) {
        MapSqlParameterSource params = new MapSqlParameterSource("todo_id", id);

        jdbcTemplate.update("call delete_todo_by_id(:todo_id); commit;", params);


    }

}
