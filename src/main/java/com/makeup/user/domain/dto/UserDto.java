package com.makeup.user.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    Long id;
    String login;
    String email;
    String type;
}
