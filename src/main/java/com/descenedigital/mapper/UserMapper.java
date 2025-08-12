package com.descenedigital.mapper;

import com.descenedigital.dto.register.RegisterRequest;
import com.descenedigital.dto.register.RegisterResponse;
import com.descenedigital.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(RegisterRequest request);
    RegisterResponse toDto(User user);
}