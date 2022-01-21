package com.insectbyte.iprwc.daos;

import com.insectbyte.iprwc.models.User;
import com.insectbyte.iprwc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserDAO {

    private final UserRepository USER_REPOSITORY;
    private final PasswordEncoder PASSWORD_ENCODER;

    @Autowired
    public UserDAO (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.USER_REPOSITORY = userRepository;
        this.PASSWORD_ENCODER = passwordEncoder;
    }

    public User createUser(User user) {
        user.setPassword(PASSWORD_ENCODER.encode(user.getPassword()));
        return this.USER_REPOSITORY.save(user);
    }

    public User getUserByName(String name) {
        Optional<User> optUser = this.USER_REPOSITORY.findUserByUsername(name);
        User user = optUser.orElseThrow();
        return user;
    }
}
