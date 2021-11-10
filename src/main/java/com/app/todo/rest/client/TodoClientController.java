package com.app.todo.rest.client;

import com.app.todo.model.Todo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/client/todo", "/client/todo/"})
public class TodoClientController {

    private TodoApiClient client;

    public TodoClientController(TodoApiClient client) {
        this.client = client;
    }

    @GetMapping
    public ModelAndView viewTodoList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/index");
        modelAndView.addObject("todoList", client.getTodoList());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView viewTodoDetails(@PathVariable(name = "id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/view-todo");
        Optional<Todo> optionalTodo = client.getTodoDetails(id);
        if (optionalTodo.isPresent()) {
            modelAndView.addObject("details", optionalTodo.get());
        }
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView addNewTodo() {
        ModelAndView modelAndView = new ModelAndView();
        Todo todo = new Todo();
        modelAndView.addObject("todo", todo);
        modelAndView.setViewName("client/add-todo");
        return modelAndView;
    }

    @PostMapping("/add")
    public String addNewTodo(@Valid @ModelAttribute("todo") Todo todoForm, BindingResult errors,
                             @RequestParam(name = "task") String task_name,
                             @RequestParam(name = "context") String context,
                             @RequestParam(name = "deadline") String deadline
    ) {

        Todo todo = new Todo();
        if (errors.hasErrors()) {
            System.out.println(errors.getAllErrors());
            return "client/add-todo";
        }
        todo.setTask(task_name);
        todo.setContext(context);
        todo.setDeadline(LocalDateTime.parse(deadline, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        client.addTodo(todo);
        System.out.println("todo added in client:" + todo);
        return "redirect:/client/todo";
    }


    @GetMapping("/{id}/update")
    public ModelAndView updateTodo(@PathVariable(name = "id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("client/update-todo");
        Optional<Todo> optionalTodo = client.getTodoDetails(id);
        if (optionalTodo.isPresent()) {
            modelAndView.addObject("todo", optionalTodo.get());
        }
        return modelAndView;
    }

    @PostMapping("/update")
    public String updateTodo(
            @Valid @ModelAttribute("todo") Todo todoForm, BindingResult errors,
            @RequestParam(name = "id") long id,
            @RequestParam(name = "task") String task_name,
            @RequestParam(name = "context") String context,
            @RequestParam(name = "deadline") String deadline) {

        if (errors.hasErrors()) {
            return "client/update-todo";
        }

        Todo todo = new Todo();
        todo.setId(id);
        todo.setTask(task_name);
        todo.setContext(context);
        todo.setDeadline(LocalDateTime.parse(deadline, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        client.updateTodo(todo);
        return "redirect:/client/todo";
    }


    @GetMapping("/{id}/delete")
    public String deleteTodo(@PathVariable(name = "id") long id) {
        client.deleteTodo(id);
        return "redirect:/client/todo";
    }

}
