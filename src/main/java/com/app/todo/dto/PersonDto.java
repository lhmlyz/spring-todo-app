package com.app.todo.dto;


import lombok.Data;

@Data
public class PersonDto {
    private Long person_id;
    private String person_name;
    private String person_surname;
    private String person_email;
}
