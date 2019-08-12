package com.makeup.user.domain.dto;

import com.makeup.user.domain.passwordValidator.ValidPassword;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserDto {
    String login;
    String email;

    @ValidPassword
    String password;
}