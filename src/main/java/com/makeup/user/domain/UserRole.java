package com.makeup.user.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String role;
}
