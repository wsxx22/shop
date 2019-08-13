package com.makeup.role.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RoleConfiguration {



    @Bean
    RoleFacade roleFacade(RoleRepository roleRepository){
        return new RoleFacade(new RoleService(roleRepository, new RoleMapperImpl()));
    }
}
