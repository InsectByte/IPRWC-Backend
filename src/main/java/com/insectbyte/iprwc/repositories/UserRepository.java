package com.insectbyte.iprwc.repositories;

import com.insectbyte.iprwc.models.User;
import org.hibernate.type.UUIDBinaryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, UUIDBinaryType> {

    Optional<User> findUserByUsername(String username);

}
