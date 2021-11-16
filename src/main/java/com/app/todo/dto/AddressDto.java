package com.app.todo.dto;

import com.app.todo.entity.Person;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressDto {
    private Long id;
    private String country;
    private String region;
    private String city;
    private String info;

}
