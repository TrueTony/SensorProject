package com.example.project3.repositories;

import com.example.project3.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findSensorByName(String name);
}
