package com.app.todo.controller;

import com.app.todo.model.Todo;
import com.app.todo.repository.jdbc.TodoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class TodoController {

    @Resource
    private TodoRepository repository;


//    @Autowired
//    TodoValidator validator;

//    @InitBinder
//    public void dataBinder(WebDataBinder dataBinder) {
//        if (dataBinder.getTarget() != null && dataBinder.getTarget().getClass().equals(Todo.class)) {
//            dataBinder.setValidator(validator);
//        }
//    }


    @GetMapping
    public ModelAndView viewTodoList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mvc/index");
        modelAndView.addObject("todoList", repository.getTodoList());
        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView viewTodoDetails(@PathVariable(name = "id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mvc/view-todo");
        Optional<Todo> optionalTodo = repository.getTodoById(id);
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
        modelAndView.setViewName("mvc/add-todo");
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
            return "mvc/add-todo";
        }
        todo.setTask(task_name);
        todo.setContext(context);
        todo.setDeadline(LocalDateTime.parse(deadline, DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        repository.addNewTodo(todo);
        System.out.println("todo added" + todo);
        return "redirect:/";
    }


    @GetMapping("/{id}/update")
    public ModelAndView updateTodo(@PathVariable(name = "id") long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("mvc/update-todo");
        Optional<Todo> optionalTodo = repository.getTodoById(id);
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
            return "mvc/update-todo";
        }

        Todo todo = new Todo();
        todo.setId(id);
        todo.setTask(task_name);
        todo.setContext(context);
        todo.setDeadline(LocalDateTime.parse(deadline, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        repository.updateTodo(todo);
        return "redirect:/";
    }


    @GetMapping("/{id}/delete")
    public String deleteTodo(@PathVariable(name = "id") long id) {
        repository.deleteTodo(id);
        return "redirect:/";
    }


}
