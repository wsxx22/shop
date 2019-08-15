package com.makeup.role.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Set;

@Configuration
class RoleConfiguration {

    @Bean
    RoleFacade roleFacade(RoleRepository roleRepository){
        return new RoleFacade(new RoleService(roleRepository, new RoleMapperImpl()));
    }

    RoleFacade roleFacade(Set<Role> db){
        MemoryRoleRepository repository = new MemoryRoleRepository(db);

        return new RoleFacade(new RoleService(repository, new RoleMapperImpl()));
    }
}
