package com.insectbyte.iprwc.controllers;

import com.insectbyte.iprwc.daos.PlanDAO;
import com.insectbyte.iprwc.dto.PlanDeleteDTO;
import com.insectbyte.iprwc.models.Plan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${end.plans}")
@CrossOrigin(origins="${origin}")
public class PlanController {
    private final PlanDAO PLAN_DAO;

    @Autowired
    public PlanController(PlanDAO planDAO) {
        this.PLAN_DAO = planDAO;
    }
//    Get Mappings
    @GetMapping
    public Optional<List<Plan>> getPlans() {
        return PLAN_DAO.getPlans();
    }
//    Post Mapping
    @Secured("ROLE_ADMIN")
    @PostMapping
    public Plan postPlan(@RequestBody Plan plan) {
        return PLAN_DAO.postPlan(plan);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping
    public ResponseEntity deletePlan(@RequestBody PlanDeleteDTO planDeleteDTO) {
        Optional<Plan> plan = this.PLAN_DAO.getPlanById(planDeleteDTO.getId());
        if (plan.isEmpty()) return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
        this.PLAN_DAO.removePlan(plan.get());
        return new ResponseEntity(HttpStatus.OK);
    }
}
