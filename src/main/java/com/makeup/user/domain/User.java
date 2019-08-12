package com.makeup.user.domain;

import com.makeup.role.domain.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String login;

    String password;

    String email;

    @Getter(value = AccessLevel.NONE)
    @ManyToMany(fetch = FetchType.EAGER)
            @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "id_user"),
                                            inverseJoinColumns = @JoinColumn(name = "id_role"))
    Set<Role> roles;

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.roles = new HashSet<>();
    }

    void addRole(Role role){
        roles.add(role);
    }

    Set<Role> getRoles() {
        return new HashSet<>(roles);
    }
}