package com.makeup.role.domain;

import com.makeup.user.domain.query.UserQueryDto;
import lombok.*;
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
@Builder
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String role;

    @Getter(AccessLevel.NONE)
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    Set<UserQueryDto> users;
}
