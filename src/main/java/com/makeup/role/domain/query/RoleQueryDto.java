package com.makeup.role.domain.query;

import com.makeup.user.domain.query.UserQueryDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class RoleQueryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String role;

    @Getter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    Set<UserQueryDto> users;

    public void addUser(UserQueryDto user){
        users.add(user);
    }

    public RoleQueryDto(Long id, String role) {
        this.id = id;
        this.role = role;
        this.users = new HashSet<>();
    }
}