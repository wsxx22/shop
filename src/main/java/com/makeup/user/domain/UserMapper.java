package com.makeup.user.domain;

import com.makeup.user.domain.dto.UserDto;
import com.makeup.user.domain.query.UserQueryDto;

import java.util.Collection;
import java.util.List;

interface UserMapper {

    UserDto toDto(User user);

//    UserQueryDto toQueryDto(User user);

    List<UserDto> toSetDto (Collection<User> users);


}
