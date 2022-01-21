package com.insectbyte.iprwc.controllers;

import com.insectbyte.iprwc.daos.UserDAO;
import com.insectbyte.iprwc.models.AuthenticationRequest;
import com.insectbyte.iprwc.models.AuthenticationResponse;
import com.insectbyte.iprwc.models.IPRWCUserDetails;
import com.insectbyte.iprwc.models.User;
import com.insectbyte.iprwc.services.IPRWCUserDetailsService;
import com.insectbyte.iprwc.services.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("${end.authenticate}")
@CrossOrigin(origins="${origin}")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;
    private IPRWCUserDetailsService userDetailsService;
    private JWTUtil jwtTokenUtil;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, IPRWCUserDetailsService iprwcUserDetailsService, JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = iprwcUserDetailsService;
        this.jwtTokenUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return new ResponseEntity(new AuthenticationResponse(jwt), HttpStatus.OK);
    }
}
