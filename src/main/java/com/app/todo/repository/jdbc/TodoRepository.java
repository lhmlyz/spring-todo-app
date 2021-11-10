package com.app.todo.repository.jdbc;

import com.app.todo.model.Todo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository {
    List<Todo> getTodoList();
    Optional<Todo> getTodoById(long id);
    boolean isTaskNameExist(String mail);
    Todo updateTodo(Todo todo);
    Todo addNewTodo(Todo todo);
    void deleteTodo(long id);
}
