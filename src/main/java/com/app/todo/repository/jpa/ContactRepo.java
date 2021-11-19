package com.app.todo.repository.jpa;

import com.app.todo.entity.Contact;
import com.app.todo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepo extends JpaRepository<Contact, Long> {

    List<Contact> findContactsByStudentId(Long id);
}
