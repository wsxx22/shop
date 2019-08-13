package com.makeup.user.domain;

import com.makeup.user.domain.query.UserQueryDto;
import org.springframework.data.repository.Repository;

import java.util.Optional;

interface UserRepository extends Repository<User, Long> {

    void save(UserQueryDto userQueryDto);

    Optional<User> findByLogin (String login);

    Optional<User> findByEmail (String email);
}