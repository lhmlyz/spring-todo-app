package com.app.todo.dto;

import com.app.todo.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactDto contactToDto(Contact contact);

    Contact dtoToContact(ContactDto contactDto);

}
