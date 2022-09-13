package com.example.project3.util;

import com.example.project3.models.Sensor;
import com.example.project3.services.SensorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorsService sensorsService;

    @Autowired
    public SensorValidator(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if (sensorsService.findSensorByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", "", "Сервис с таким именем уже создан");
        }
    }

    public void validateExist(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if (sensorsService.findSensorByName(sensor.getName()).isEmpty()) {
            errors.rejectValue("sensor", "", "Сервиса с таикм именем нет");
        }
    }
}
