package com.app.todo.rest.api;


import com.app.todo.model.Todo;
import com.app.todo.repository.jdbc.TodoRepository;
import com.app.todo.webconfig.TodoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = {"/api/todo", "/api/todo/"})
public class TodoRestController {

    @Qualifier("todoRepositoryImpl")
    @Autowired
    private TodoRepository repository;
    private TodoConfig config;


    @GetMapping()
    public List<Todo> getTodoList() {
        return repository.getTodoList();
    }

    @GetMapping("/{id:[\\d]+}")
    public Todo getTodoDetails(@PathVariable(name = "id") long id) {
        Optional<Todo> optionalTodo = repository.getTodoById(id);
        if (optionalTodo.isPresent()) {
            return optionalTodo.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Task with id %s not found", id));
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public Todo addTodo(@RequestBody @Valid Todo todo, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.valueOf(errors.getFieldError()));
        } else {
            repository.addNewTodo(todo);
            System.out.println(todo);
            return todo;
        }
    }

    @PutMapping("/")
    public Todo updateTodo(@RequestBody @Valid Todo todo, BindingResult errors) {
        Optional<Todo> todoOptional = repository.getTodoById(todo.getId());
        System.out.println(todoOptional);
        if (todoOptional.isPresent()) {
            if (!errors.hasErrors()) {
                repository.updateTodo(todo);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.valueOf(errors.getFieldError()));
            }
        } else {
            if (!errors.hasErrors()) {
                repository.addNewTodo(todo);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.valueOf(errors.getFieldError()));
            }
        }
        return todo;
    }

    @DeleteMapping("/{id}")
    public Todo deleteTodo(@PathVariable(name = "id") long id) {
        Optional<Todo> todoOptional = repository.getTodoById(id);
        if (todoOptional.isPresent()) {
            repository.deleteTodo(id);
            return todoOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo not available");
        }
    }

    @PostMapping("/{id}/task")
    public void uploadTask(@PathVariable(name = "id") long id, @RequestParam(name = "photo") MultipartFile photo) {
        Optional<Todo> optionalTodo = repository.getTodoById(id);
        if (optionalTodo.isPresent()) {
            Todo todo = optionalTodo.get();
            String newName = "C:\\Users\\ilham\\IdeaProjects\\todo\\uploads\\" + UUID.randomUUID().toString() + photo.getContentType().replace("/", ".");

            try {
                Files.copy(photo.getInputStream(), Paths.get(newName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
            repository.updateTodo(todo);
            System.out.println(newName);
        }

    }
}