package com.makeup.user.domain;

import com.makeup.user.domain.dto.UserDto;

import java.util.Collection;
import java.util.List;

public class UserMapperImpl implements UserMapper {
    @Override
    public UserDto toDto(User user) {
        return null;
    }

    @Override
    public List<UserDto> toSetDto(Collection<User> users) {
        return null;
    }
}
