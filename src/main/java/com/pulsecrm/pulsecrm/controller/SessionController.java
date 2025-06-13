package com.pulsecrm.pulsecrm.controller;

import com.pulsecrm.pulsecrm.model.Session;
import com.pulsecrm.pulsecrm.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> getSessionById(@PathVariable Long id) {
        Optional<Session> session = sessionRepository.findById(id);
        if (session.isPresent()) {
            return new ResponseEntity<>(session.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Session createSession(@RequestBody Session session) {
        return sessionRepository.save(session);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Session> updateSession(@PathVariable Long id, @RequestBody Session sessionDetails) {
        Optional<Session> optionalSession = sessionRepository.findById(id);
        if (optionalSession.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Session session = optionalSession.get();
        session.setClient(sessionDetails.getClient());
        session.setDateTime(sessionDetails.getDateTime());
        session.setSessionType(sessionDetails.getSessionType());
        session.setInstructor(sessionDetails.getInstructor());
        session.setStatus(sessionDetails.getStatus());

        Session UpdatedSession = sessionRepository.save(session);
        return ResponseEntity.ok(UpdatedSession);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Session> deleteSession(@PathVariable Long id) {
        if (!sessionRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sessionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
