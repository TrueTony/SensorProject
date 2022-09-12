package com.example.project3.services;

import com.example.project3.models.Sensor;
import com.example.project3.repositories.SensorsRepository;
import com.example.project3.util.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {

    private final SensorsRepository sensorsRepository;

    @Autowired
    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    public Sensor findOne(int id) {
        Optional<Sensor> foundSensor = sensorsRepository.findById(id);
        return foundSensor.orElseThrow(SensorNotFoundException::new);
    }

    public List<Sensor> findAll() {
        return sensorsRepository.findAll();
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorsRepository.save(sensor);
    }

}
