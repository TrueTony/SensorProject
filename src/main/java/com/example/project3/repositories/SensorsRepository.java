package com.example.project3.repositories;

import com.example.project3.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
}
