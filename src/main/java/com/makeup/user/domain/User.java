package com.makeup.user.domain;

import com.makeup.user.domain.passwordValidator.ValidPassword;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
            @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "id_user"),
                                            inverseJoinColumns = @JoinColumn(name = "id_role"))
    Set<UserRole> userRoles;

    public User(String login, String password, String email, Set<UserRole> userRoles) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.userRoles = userRoles;
    }
}
