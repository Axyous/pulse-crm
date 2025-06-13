package com.pulsecrm.pulsecrm.repository;

import com.pulsecrm.pulsecrm.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
