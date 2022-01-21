package com.insectbyte.iprwc.repositories;

import com.insectbyte.iprwc.models.Role;
import org.hibernate.type.UUIDBinaryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUIDBinaryType> {
    @Query(value = "SELECT * FROM role WHERE name = ? ;", nativeQuery = true)
    Role findRoleByName(@Param("name") String name);
}
