package com.makeup.user.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    UserFacade userFacade(UserRepository userRepository,
                          UserMapper userMapper,
                          UserRoleRepository userRoleRepository) {
        return new UserFacade(
                new UserService(
                        userMapper, userRepository,
                        new UserFactory(userRoleRepository), new UserValidator(userRepository)));
    }
}
