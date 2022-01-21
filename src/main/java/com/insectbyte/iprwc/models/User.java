package com.insectbyte.iprwc.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    private String username;
    private String fullname;
    private String email;
    private String password;
    private Date birthdate;
    private String zipcode;
    private String number;
    private String country;
    @ManyToOne
    @JoinColumn(name="role", nullable = false)
    private Role role;
    private boolean enabled = true;

    public User() {}

    public User(UUID id, String username, String fullname, String email, String password, Date birthdate, String zipcode, String number, String country, Role role, boolean enabled) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.zipcode = zipcode;
        this.number = number;
        this.country = country;
        this.role = role;
        this.enabled = enabled;
    }

    public User(String username, String fullname, String email, String password, Date birthdate, String zipcode, String number, String country, Role role, boolean enabled) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.zipcode = zipcode;
        this.number = number;
        this.country = country;
        this.role = role;
        this.enabled = enabled;
    }

    public User(UUID id, String username, String fullname, String email, String password, Date birthdate, String zipcode, String number, String country, boolean enabled) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.zipcode = zipcode;
        this.number = number;
        this.country = country;
        this.enabled = enabled;
    }

    public User(String username, String fullname, String email, String password, Date birthdate, String zipcode, String number, String country, boolean enabled) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.birthdate = birthdate;
        this.zipcode = zipcode;
        this.number = number;
        this.country = country;
        this.enabled = enabled;
    }
}
