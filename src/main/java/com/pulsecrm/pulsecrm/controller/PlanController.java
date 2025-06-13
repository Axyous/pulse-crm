package com.pulsecrm.pulsecrm.controller;

import com.pulsecrm.pulsecrm.model.Plan;
import com.pulsecrm.pulsecrm.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plans")
public class PlanController {

    @Autowired
    private PlanRepository planRepository;

    @GetMapping
    public List<Plan> getAllPlans() {
        return planRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable Long id) {
        Optional<Plan> plan = planRepository.findById(id);
        return plan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Plan createPlan(@RequestBody Plan plan) {
        return planRepository.save(plan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Plan> updatePlan(@PathVariable Long id, @RequestBody Plan plandetails) {
        Optional<Plan> optionalPlan = planRepository.findById(id);
        if (optionalPlan.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Plan plan = optionalPlan.get();
        plan.setPlanName(plandetails.getPlanName());
        plan.setPlanDescription(plandetails.getPlanDescription());
        plan.setPlanPrice(plandetails.getPlanPrice());
        plan.setPlanDuration(plandetails.getPlanDuration());

        return ResponseEntity.ok(planRepository.save(plan));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Plan> deletePlan(@PathVariable Long id) {
        if (!planRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        planRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
