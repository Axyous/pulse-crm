package com.pulsecrm.pulsecrm.controller;

import com.pulsecrm.pulsecrm.model.Instructor;
import com.pulsecrm.pulsecrm.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/instructors")
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepository;

    @GetMapping
    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Long id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        return instructor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Instructor createInstructor(@RequestBody Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    @PutMapping("{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @RequestBody Instructor details) {
        Optional<Instructor> optionalInstructor = instructorRepository.findById(id);

        if (optionalInstructor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Instructor instructor = optionalInstructor.get();
        instructor.setName(instructor.getName());
        instructor.setSpecialty(instructor.getSpecialty());
        instructor.setPhone(instructor.getPhone());

        return ResponseEntity.ok(instructorRepository.save(instructor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        if (!instructorRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        instructorRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
