package com.pulsecrm.pulsecrm.repository;

import com.pulsecrm.pulsecrm.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findByClientId(Long clientId);

    List<Attendance> findByAttendanceDate(LocalDate date);

    List<Attendance> findByAttendanceDateBetween(LocalDate start, LocalDate end);

    List<Attendance> findByClientIdAndAttendanceDate(Long clientId, LocalDate date);

    List<Attendance> findByClientIdAndAttendanceDateBetween(Long clientId, LocalDate start, LocalDate end);
}
