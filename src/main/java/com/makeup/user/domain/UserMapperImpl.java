package com.makeup.user.domain;

import com.makeup.user.domain.dto.UserDto;
import com.makeup.user.domain.query.UserQueryDto;

import java.util.Collection;
import java.util.List;

class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User user) {
        return null;
    }

//    @Override
//    public UserQueryDto toQueryDto(User user) {
//        return null;
//    }

    @Override
    public List<UserDto> toSetDto(Collection<User> users) {
        return null;
    }
}
