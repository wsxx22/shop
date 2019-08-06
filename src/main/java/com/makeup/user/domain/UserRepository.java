package com.makeup.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin (String login);

    Optional<User> findByEmail (String email);
}
