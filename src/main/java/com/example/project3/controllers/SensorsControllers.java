package com.example.project3.controllers;

import com.example.project3.dto.SensorDTO;
import com.example.project3.models.Sensor;
import com.example.project3.services.SensorsService;
import com.example.project3.util.SensorErrorResponse;
import com.example.project3.util.SensorNotCreatedException;
import com.example.project3.util.SensorNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorsControllers {

    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorsControllers(SensorsService sensorsService, ModelMapper modelMapper) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<SensorDTO> index() {
        return sensorsService.findAll().stream().map(this::convertToSensorDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public SensorDTO findOne(@PathVariable("id") int id) {
        return convertToSensorDTO(sensorsService.findOne(id));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> addSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError e: errors) {
                errorMsg.append(e.getField())
                        .append(" - ")
                        .append(e.getDefaultMessage())
                        .append(";");
            }
            throw new SensorNotCreatedException(errorMsg.toString());
        }
        sensorsService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotFoundException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                "Sensor with this ID wasn't found",
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        SensorErrorResponse response = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
