package com.epam.dao;

import com.epam.domain.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends AbstractRepository<Role> {
    Optional<Role> findRoleByNameIgnoreCase(String name);
}
