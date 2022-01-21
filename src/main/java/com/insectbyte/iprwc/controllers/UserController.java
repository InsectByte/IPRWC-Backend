package com.insectbyte.iprwc.controllers;

import com.insectbyte.iprwc.daos.RoleDAO;
import com.insectbyte.iprwc.daos.UserDAO;
import com.insectbyte.iprwc.models.IPRWCUserDetails;
import com.insectbyte.iprwc.models.User;
import com.insectbyte.iprwc.services.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.Map;

@RestController
@RequestMapping("${end.user}")
@CrossOrigin(origins="${origin}")
public class UserController {

    private final UserDAO USER_DAO;
    private final JWTUtil JWT_UTIL;
    private final RoleDAO ROLE_DAO;

    @Autowired
    public UserController (UserDAO userDAO, JWTUtil jwtUtil, RoleDAO roleDAO) {
        this.USER_DAO = userDAO;
        this.JWT_UTIL = jwtUtil;
        this.ROLE_DAO = roleDAO;
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) {
        if (isEmail(user.getEmail())) {
            user.setRole(this.ROLE_DAO.getRole("ROLE_USER"));
            return new ResponseEntity(this.USER_DAO.createUser(user), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/admin")
    public ResponseEntity createAdmin(@RequestBody User user) {
        if (isEmail(user.getEmail())) {
            user.setRole(this.ROLE_DAO.getRole("ROLE_ADMIN"));
            return new ResponseEntity(this.USER_DAO.createUser(user), HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean isEmail(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
            return true;
        } catch (AddressException ex) {
            return false;
        }
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity getuser(@RequestHeader Map<String, String> header) {
        String jwt = header.get("authorization").substring(7);
        User user = this.USER_DAO.getUserByName(JWT_UTIL.extractUsername(jwt));
        user.setPassword(""); user.setRole(null);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @GetMapping("/validate")
    public ResponseEntity validateToken(@RequestHeader Map<String, String> header) {
        String jwt = header.get("authorization").substring(7);
        User user = USER_DAO.getUserByName(JWT_UTIL.extractUsername(jwt));
        IPRWCUserDetails userDetails = new IPRWCUserDetails(user);
        return new ResponseEntity(this.JWT_UTIL.validateToken(jwt, userDetails),HttpStatus.OK);
    }
}
