package com.pulsecrm.pulsecrm.repository;

import com.pulsecrm.pulsecrm.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
