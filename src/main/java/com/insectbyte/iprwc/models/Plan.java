package com.insectbyte.iprwc.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@ToString
@Getter
@Setter
public class Plan {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private String name;
    private String description;
    private boolean recommended;
    private int cpuCount;
    private int ramAmount;
    private int storageAmount;
    private float price;

    public Plan() { }

    public Plan(UUID id, String name, String description, boolean recommended, int cpuCount, int ram_amount, int storage_amount, float price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.recommended = recommended;
        this.cpuCount = cpuCount;
        this.ramAmount = ram_amount;
        this.storageAmount = storage_amount;
        this.price = price;
    }

    public Plan(String name, String description, boolean recommended, int cpuCount, int ram_amount, int storage_amount, float price) {
        this.name = name;
        this.description = description;
        this.recommended = recommended;
        this.cpuCount = cpuCount;
        this.ramAmount = ram_amount;
        this.storageAmount = storage_amount;
        this.price = price;
    }
}
