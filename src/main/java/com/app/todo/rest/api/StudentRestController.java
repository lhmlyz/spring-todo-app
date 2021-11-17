package com.app.todo.rest.api;

import com.app.todo.entity.Student;
import com.app.todo.repository.jpa.ContactRepo;
import com.app.todo.repository.jpa.StudentRepo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("student")
public class StudentRestController {

    private final StudentRepo studentRepo;
    private final ContactRepo contactRepo;

    public StudentRestController(StudentRepo studentRepo, ContactRepo contactRepo) {
        this.studentRepo = studentRepo;
        this.contactRepo = contactRepo;
    }


    @GetMapping
    public List<Student> findStudents() {
        return studentRepo.findAll();
    }

    @PostMapping
    public Student saveStudents(@RequestBody Student student) {
        return studentRepo.save(student);
    }


    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable(name = "id") Long id, @RequestBody Student student) {
        return studentRepo.save(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable(name = "id") Long id) {
        studentRepo.deleteById(id);
    }
}
