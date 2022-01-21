package com.insectbyte.iprwc.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "`order`")
@Data
public class Order {
    @GeneratedValue
    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    private Date createdDate;

    @ManyToOne
    @JoinColumn(name="plan_id")
    private Plan plan;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order(Plan plan, User user) {
        this.createdDate = new Date();
        this.plan = plan;
        this.user = user;
    }

    public Order() {

    }
}
