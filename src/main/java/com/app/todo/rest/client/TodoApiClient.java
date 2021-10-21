package com.app.todo.rest.client;

import com.app.todo.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoApiClient {
    List<Todo> getTodoList();

    Optional<Todo> getTodoDetails(long id);

    Todo addTodo(Todo todo);

    Todo updateTodo(Todo todo);

    Todo deleteTodo(long id);
}
