package com.makeup.user.domain;

import org.springframework.data.repository.Repository;

import java.util.Optional;

interface UserRepository extends Repository<User, Long> {

    void save(User user);

    Optional<User> findByLoginAndPassword (String login, String password);

    Optional<User> findByLogin (String login);

    Optional<User> findByEmail (String email);
}