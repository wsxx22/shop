package com.makeup.user.domain;

import com.makeup.role.domain.Role;
import com.makeup.user.domain.passwordValidator.ValidPassword;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String login;

    @ValidPassword
    String password;
    String email;

    @ManyToMany(cascade = CascadeType.ALL)
            @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "id_user"),
                                            inverseJoinColumns = @JoinColumn(name = "id_role"))
    Set<Role> roles = new HashSet<>();

    void addRole(Role role) {
        roles.add(role);
    }
}