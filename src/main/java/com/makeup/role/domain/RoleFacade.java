package com.makeup.role.domain;

import com.makeup.role.domain.dto.RoleDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.transaction.Transactional;
import java.util.Set;

@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class RoleFacade {
    RoleService roleService;

    public RoleDto findRole(String role){
        return roleService.findRole(role);
    }

    public Set<RoleDto> findRoles(){
        return roleService.findRoles();
    }
}
