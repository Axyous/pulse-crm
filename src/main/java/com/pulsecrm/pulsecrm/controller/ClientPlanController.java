package com.pulsecrm.pulsecrm.controller;

import com.pulsecrm.pulsecrm.model.ClientPlan;
import com.pulsecrm.pulsecrm.repository.ClientPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;



@RestController
@RequestMapping("/client-plans")
public class ClientPlanController {

    @Autowired
    private ClientPlanRepository clientPlanRepository;

    @GetMapping
    public List<ClientPlan> getAllClientPlans() {
        return clientPlanRepository.findAll();
    }

    @GetMapping("/{id}")
    public ClientPlan getClientPlanById(@PathVariable Long id) {
        return clientPlanRepository.findById(id).orElse(null);
    }

    @GetMapping("/client/{clientId}")
    public List<ClientPlan> getPlansByClientId(@PathVariable Long clientId) {
        return clientPlanRepository.findByClientId(clientId);
    }

    @GetMapping("/current-plan/{clientId}")
    public ResponseEntity<ClientPlan> getCurrentPlan(@PathVariable Long clientId) {
        LocalDate today = LocalDate.now();
        return clientPlanRepository
                .findTopByClientIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByStartDateDesc(clientId, today, today)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ClientPlan createClientPlan(@RequestBody ClientPlan clientPlan) {
        return clientPlanRepository.save(clientPlan);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientPlan> updateClientPlan(@PathVariable Long id, @RequestBody ClientPlan updatedClientPlan) {
        return clientPlanRepository.findById(id).map(clientPlan -> {
            clientPlan.setClient(updatedClientPlan.getClient());
            clientPlan.setPlan(updatedClientPlan.getPlan());
            clientPlan.setStartDate(updatedClientPlan.getStartDate());
            clientPlan.setEndDate(updatedClientPlan.getEndDate());
            ClientPlan saved = clientPlanRepository.save(clientPlan);
            return ResponseEntity.ok(saved);
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteClientPlan(@PathVariable Long id) {
        clientPlanRepository.deleteById(id);
    }
}
