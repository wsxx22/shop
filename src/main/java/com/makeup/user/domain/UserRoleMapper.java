package com.makeup.user.domain;

import com.makeup.user.domain.dto.UserRoleDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
interface UserRoleMapper {

    UserRoleDto toDto (UserRole userRole);

    List<UserRoleDto> toDtoList (List<UserRole> userRoles);
}
