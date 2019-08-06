package com.makeup.user.domain;

import com.makeup.user.domain.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
interface UserMapper {

    UserDto toDto(User user);

    List<UserDto> toSetDto (Collection<User> users);
}
