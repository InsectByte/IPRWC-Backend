package com.insectbyte.iprwc.repositories;

import com.insectbyte.iprwc.models.Order;
import org.hibernate.type.UUIDBinaryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUIDBinaryType> {

    @Query(value = "SELECT * FROM `order` WHERE user_id = ?", nativeQuery = true)
    ArrayList<Order> getByUserId(UUID userId);
}
