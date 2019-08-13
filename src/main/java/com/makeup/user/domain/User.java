package com.makeup.user.domain;

import com.makeup.role.domain.query.RoleQueryDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Builder
@DynamicUpdate
@DynamicInsert
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
class User {

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

//    User(String login, String password, String email) {
//        this.login = login;
//        this.password = password;
//        this.email = email;
//        this.roles = new HashSet<>();
//    }

//    void addRole(RoleQueryDto role){
//        roles.add(role);
//    }
//
    Set<RoleQueryDto> getRoles() {
        return new HashSet<>(roles);
    }
//
    void changePassword(String password){
        this.password = password;
    }

//    @Override
//    public UserQueryDto entityToQueryDto() {
//        return null;
//    }
}