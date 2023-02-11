package com.epam.dao;

import com.epam.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends AbstractRepository<User> {
    Optional<User> findUserByLoginIgnoreCase(String login);
}
