package com.makeup.user.domain.query;

import com.makeup.role.domain.query.RoleQueryDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserQueryDto {

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
    Set<RoleQueryDto> roles;

    public UserQueryDto(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.roles = new HashSet<>();
    }

    public void addRole(RoleQueryDto role){
        roles.add(role);
    }

    public Set<RoleQueryDto> getRoles() {
        return new HashSet<>(roles);
    }

    public void changePassword(String password){
        this.password = password;
    }
}
