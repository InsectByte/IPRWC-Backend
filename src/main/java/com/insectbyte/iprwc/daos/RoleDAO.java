package com.insectbyte.iprwc.daos;

import com.insectbyte.iprwc.models.Role;
import com.insectbyte.iprwc.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleDAO {
    private final RoleRepository ROLE_REPOSITORY;

    @Autowired
    public RoleDAO (RoleRepository roleRepository) {
        this.ROLE_REPOSITORY = roleRepository;
    }

    public Role getRole(String name) {
        return this.ROLE_REPOSITORY.findRoleByName(name);
    }
}
