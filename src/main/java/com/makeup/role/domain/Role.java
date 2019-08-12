package com.makeup.role.domain;

import com.makeup.user.domain.User;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.lang.annotation.Documented;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String role;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    Set<User> users;

    public void addUser(User user){
        users.add(user);
    }

    public Role(Long id, String role) {
        this.id = id;
        this.role = role;
        this.users = new HashSet<>();
    }
}
