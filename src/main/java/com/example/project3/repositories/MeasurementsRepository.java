package com.example.project3.repositories;

import com.example.project3.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeasurementsRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findAllByRainingIsTrue();
}
