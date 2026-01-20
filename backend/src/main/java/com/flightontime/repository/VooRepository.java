package com.flightontime.repository;

import com.flightontime.model.Voo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VooRepository extends JpaRepository<Voo, Long> {
    List<Voo> findByDepartureDateBetween(LocalDateTime inicio, LocalDateTime fim);
}