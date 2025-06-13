package com.pulsecrm.pulsecrm.controller;

import com.pulsecrm.pulsecrm.model.Attendance;
import com.pulsecrm.pulsecrm.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/attendances")
public class AttendanceController {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @GetMapping
    public List<Attendance> getAllAttendances() {
        return attendanceRepository.findAll();
    }

    @GetMapping("/{id}")
    public Attendance getAttendance(@PathVariable Long id) {
        return attendanceRepository.findById(id).orElse(null);
    }

    @GetMapping("/search")
    public List<Attendance> searchAttendances(
            @RequestParam(required = false) Long clientId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {

        if (clientId != null && date != null) {
            return attendanceRepository.findByClientIdAndAttendanceDate(clientId, date);
        } else if (clientId != null && start != null && end != null) {
            return attendanceRepository.findByClientIdAndAttendanceDateBetween(clientId, start, end);
        } else if (clientId != null) {
            return attendanceRepository.findByClientId(clientId);
        } else if (date != null) {
            return attendanceRepository.findByAttendanceDate(date);
        } else if (start != null && end != null) {
            return attendanceRepository.findByAttendanceDateBetween(start, end);
        } else {
            return attendanceRepository.findAll();
        }
    }

    @PostMapping
    public Attendance addAttendance(@RequestBody Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @PutMapping("/{id}")
    public Attendance updateAttendance(@PathVariable Long id, @RequestBody Attendance updatedAttendance) {
        return attendanceRepository.findById(id).map(attendance -> {
            attendance.setClient(updatedAttendance.getClient());
            attendance.setSession(updatedAttendance.getSession());
            attendance.setAttendanceDate(updatedAttendance.getAttendanceDate());
            attendance.setPresent(updatedAttendance.isPresent());
            return attendanceRepository.save(attendance);
        }).orElse(null);
    }

    @DeleteMapping("/{id}")
    public void deleteAttendance(@PathVariable Long id) {
        attendanceRepository.deleteById(id);
    }
}
