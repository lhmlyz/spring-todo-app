package com.app.todo.dto;

import com.app.todo.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address dtoToAddress(AddressDto addressDto);

    AddressDto addressToDto(Address address);

}
