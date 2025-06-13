package com.pulsecrm.pulsecrm.repository;

import com.pulsecrm.pulsecrm.model.ClientPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientPlanRepository extends JpaRepository<ClientPlan, Long> {
    List<ClientPlan> findByClientId(Long clientId);

    Optional<ClientPlan> findTopByClientIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByStartDateDesc(
            Long clientId, LocalDate today1, LocalDate today2
    );
}