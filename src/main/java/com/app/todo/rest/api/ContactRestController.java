package com.app.todo.rest.api;


import com.app.todo.entity.Contact;
import com.app.todo.repository.jpa.ContactRepo;
import com.app.todo.repository.jpa.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("student")
public class ContactRestController {
    private StudentRepo studentRepo;
    private ContactRepo contactRepo;

    @Autowired
    public ContactRestController(StudentRepo studentRepo, ContactRepo contactRepo) {
        this.studentRepo = studentRepo;
        this.contactRepo = contactRepo;
    }

    @GetMapping("{student_id}/contacts")
    public List<Contact> getStudentContacts(@PathVariable(name = "student_id ") Long id) {
        return contactRepo.findContactsByStudentId(id);
    }

 }
