package com.pulsecrm.pulsecrm.repository;

import com.pulsecrm.pulsecrm.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long>{
}
