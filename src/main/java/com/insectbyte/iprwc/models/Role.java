package com.insectbyte.iprwc.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;
import java.util.UUID;

@Entity
@Table
@Data
public class Role {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;
    @Column(unique = true, nullable = false)
    private String name;
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private Set<User> users;

    public Role () {}

    public Role (UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
