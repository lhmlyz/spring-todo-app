package com.app.todo.dto;

import com.app.todo.entity.Student;
import lombok.Data;

@Data
public class ContactDto {
    private Long id;
    private String type;
    private String value;
    private Student student;
}
