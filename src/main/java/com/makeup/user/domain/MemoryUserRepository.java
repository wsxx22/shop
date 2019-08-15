package com.makeup.user.domain;

import com.makeup.user.domain.query.UserQueryDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
class MemoryUserRepository implements UserRepository {

    ConcurrentHashMap<String, User> map;

    @Override
    public void save(UserQueryDto userQueryDto) {
        User user = User.builder()
                .id(userQueryDto.getId())
                .login(userQueryDto.getLogin())
                .password(userQueryDto.getPassword())
                .email(userQueryDto.getEmail())
                .roles(userQueryDto.getRoles()).build();
        map.put(user.getLogin(), user);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return map.entrySet().stream()
                .filter(stringUserEntry -> stringUserEntry.getKey().equals(login))
                .findFirst()
                .map(Map.Entry::getValue);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return map.entrySet().stream()
                .filter(stringUserEntry -> stringUserEntry.getValue().getEmail().equals(email))
                .findFirst()
                .map(Map.Entry::getValue);
    }
}
