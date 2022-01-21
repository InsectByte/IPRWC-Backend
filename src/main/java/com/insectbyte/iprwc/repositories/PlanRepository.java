package com.insectbyte.iprwc.repositories;

import com.insectbyte.iprwc.models.Plan;
import org.hibernate.type.UUIDBinaryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public interface PlanRepository extends JpaRepository<Plan, UUIDBinaryType> {
    @Query(value = "SELECT * FROM plan ORDER BY price ASC", nativeQuery = true)
    Optional<List<Plan>> getPlans();

    @Query(value = "SELECT * FROM plan WHERE id = ?", nativeQuery = true)
    Optional<Plan> findById(UUID productId);
}
