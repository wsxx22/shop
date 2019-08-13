package com.makeup.role.domain;

import com.makeup.user.domain.query.UserQueryDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@DynamicInsert
@DynamicUpdate
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String role;

    @Getter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    Set<UserQueryDto> users;

//    void addUser(UserQueryDto userQueryDto){
//        users.add(userQueryDto);
//    }
//
//    Role(Long id, String role) {
//        this.id = id;
//        this.role = role;
//        this.users = new HashSet<>();
//    }
}
