package com.epam.service;

import com.epam.dao.RoleRepository;
import com.epam.domain.Role;
import com.epam.exception.RoleNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getRoleByName(String name) {
        return roleRepository.findRoleByNameIgnoreCase(name).orElseThrow(() -> new RoleNotFoundException("role.name.not.found", name));
    }
}
