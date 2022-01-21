package com.insectbyte.iprwc.services;

import com.insectbyte.iprwc.models.IPRWCUserDetails;
import com.insectbyte.iprwc.models.User;
import com.insectbyte.iprwc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class IPRWCUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public IPRWCUserDetailsService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByUsername(username);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return user.map(IPRWCUserDetails::new).get();
    }
}
