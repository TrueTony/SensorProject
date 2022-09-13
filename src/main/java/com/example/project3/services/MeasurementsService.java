package com.example.project3.services;

import com.example.project3.models.Measurement;
import com.example.project3.repositories.MeasurementsRepository;
import com.example.project3.util.MeasurementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;
    private final SensorsService sensorsService;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository, SensorsService sensorsService) {
        this.measurementsRepository = measurementsRepository;
        this.sensorsService = sensorsService;
    }

    public Measurement findOne(int id) {
        Optional<Measurement> measurement = measurementsRepository.findById(id);
        return measurement.orElseThrow(MeasurementNotFoundException::new);
    }

    public List<Measurement> findAll() {
        return measurementsRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement) {
        measurement.setSensor(sensorsService.findSensorByName(measurement.getSensor().getName()).get());
        measurement.setCreatedAt(LocalDateTime.now());
        measurementsRepository.save(measurement);
    }

    public List<Measurement> findAllRaining() {
        return measurementsRepository.findAllByRainingIsTrue();
    }

}
