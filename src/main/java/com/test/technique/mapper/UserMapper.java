package com.test.technique.mapper;

import com.test.technique.dto.UserDTO;
import com.test.technique.dto.UserResponseDTO;
import com.test.technique.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "birthdate", source = "dto.birthdate")
    User dtoToEntity(UserDTO dto);

    UserResponseDTO entityToResponseDTO(User user);
}