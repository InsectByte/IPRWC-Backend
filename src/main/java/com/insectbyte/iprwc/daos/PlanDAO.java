package com.insectbyte.iprwc.daos;

import com.insectbyte.iprwc.exceptions.ProductNotFoundException;
import com.insectbyte.iprwc.models.Plan;
import com.insectbyte.iprwc.repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PlanDAO {
    private final PlanRepository PLAN_REPOSITORY;

    @Autowired
    public PlanDAO (PlanRepository planRepository) {
        this.PLAN_REPOSITORY = planRepository;
    }

    public Optional<List<Plan>> getPlans() { return PLAN_REPOSITORY.getPlans(); }

    public Plan postPlan(Plan plan) {
        return PLAN_REPOSITORY.save(plan);
    }

    public Plan findById(UUID id) {
        Optional<Plan> optPlan = PLAN_REPOSITORY.findById(id);
        if (optPlan.isEmpty()) {
            throw new ProductNotFoundException("Product ID is invalid");
        }
        return optPlan.get();
    }

    public Optional<Plan> getPlanById(UUID id) {
        return getPlanById(id);
    }

    public void removePlan(Plan plan) {
        this.PLAN_REPOSITORY.delete(plan);
    }
}
