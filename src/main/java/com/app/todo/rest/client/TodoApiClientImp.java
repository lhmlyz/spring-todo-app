package com.app.todo.rest.client;

import com.app.todo.model.Todo;
import com.app.todo.webconfig.TodoConfig;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class TodoApiClientImp implements TodoApiClient {
    private final TodoConfig config;
    private final TodoClientErrorHandler errorHandler;

    public TodoApiClientImp(TodoConfig config, TodoClientErrorHandler errorHandler) {
        this.config = config;
        this.errorHandler = errorHandler;
    }


    @Override
    public List<Todo> getTodoList() {
        RestTemplate template = new RestTemplate();
        template.setErrorHandler(errorHandler);
        System.out.println("todo rest api getTodoList cagirildi");
        var list = template.getForObject(config.getTodoApiBaseUrl(), List.class);
        System.out.println("todo rest api getTodoList response = " + config.getTodoApiBaseUrl());
        return list;
    }

    @Override
    public Optional<Todo> getTodoDetails(long id) {
        RestTemplate template = new RestTemplate();

        System.out.println("rest api getTodoDetails cagirildi with id: " + id);
        var todo = template.getForObject(config.getTodoApiBaseUrl() + "/" + id, Todo.class);
        System.out.println(config.getTodoApiBaseUrl() + id);
        System.out.println("todo rest api getTodoDetails response = " + todo);
        return Optional.of(todo);
    }

    @Override
    public Todo addTodo(Todo todo) {
        RestTemplate template = new RestTemplate();
        System.out.println("rest api addTodo started");
        System.out.println(config.getTodoApiBaseUrl());
        template.postForObject(config.getTodoApiBaseUrl(), todo, Todo.class);
        System.out.println("rest api addTodo finished: " + todo);
        return todo;
    }

    @Override
    public Todo updateTodo(Todo todo) {
        RestTemplate template = new RestTemplate();
        System.out.println("rest api updateTodo started");
        System.out.println(config.getTodoApiBaseUrl());
        HttpEntity<Todo> todoHttpEntity = new HttpEntity<>(todo);
        template.exchange(config.getTodoApiBaseUrl(), HttpMethod.PUT, todoHttpEntity, Todo.class);
        System.out.println("rest api updateTodo finished: " + todo);
        return todo;
    }

    @Override
    public Todo deleteTodo(long id) {
        RestTemplate template = new RestTemplate();
        String url = config.getTodoApiBaseUrl() + id;
        System.out.println(url);
        System.out.println("rest api deleteTodo started");
        template.delete(url);
        System.out.println("rest api deleteTodo finished");
        return null;
    }
}
