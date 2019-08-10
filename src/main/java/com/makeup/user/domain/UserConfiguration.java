package com.makeup.user.domain;

import com.makeup.role.domain.RoleFacade;
import com.makeup.user.domain.passwordValidator.PasswordConstraintValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    @Bean
    PasswordConstraintValidator passwordConstraintValidator(){
        return new PasswordConstraintValidator();
    }

    @Bean
    UserFacade userFacade(UserRepository userRepository,
                          RoleFacade roleFacade) {
        return new UserFacade(
                new UserService(
                        userRepository,
                        new UserFactory(roleFacade),
                        new UserValidator(userRepository)));
    }
}
