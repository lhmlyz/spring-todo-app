package com.app.todo.dto;

import com.app.todo.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    Person dtoToPerson(PersonDto personDto);

    @Mapping(source = "id", target = "person_id")
    @Mapping(source = "name", target = "person_name")
    @Mapping(source = "surname", target = "person_surname")
    @Mapping(source = "email", target = "person_email")
    PersonDto personToDto(Person person);


}
