package com.app.todo.dto;


import com.app.todo.entity.Address;
import lombok.Data;

@Data
public class PersonDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private AddressDto address;

}
