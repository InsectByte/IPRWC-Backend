package com.insectbyte.iprwc.repositories;

import com.insectbyte.iprwc.models.Cart;
import org.hibernate.type.UUIDBinaryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, UUIDBinaryType> {

    @Query(value = "SELECT * FROM cart WHERE user_id = ? ORDER BY created_date DESC", nativeQuery = true)
    ArrayList<Cart> getById(UUID id);

    @Query(value = "DELETE FROM cart WHERE user_id = ?1 AND id = ?2", nativeQuery = true)
    void removeFromCartById(UUID userId, UUID cartId);

    @Query(value = "SELECT * FROM cart WHERE id = ?", nativeQuery = true)
    Optional<Cart> existsById(UUID id);

    @Query(value = "UPDATE cart SET quantity = ?2 WHERE id = ?1", nativeQuery = true)
    void updateQuantityInCartById(UUID id, int value);

    @Query(value = "DELETE FROM cart WHERE user_id = ?", nativeQuery = true)
    void removeByUserId(UUID id);
}
