package com.app.todo.dto;

import com.app.todo.entity.Contact;
import lombok.Data;

import java.util.Set;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private String surname;
    private Set<Contact> contacts;
}
