package com.example.project3.controllers;

import com.example.project3.dto.MeasurementDTO;
import com.example.project3.models.Measurement;
import com.example.project3.models.Sensor;
import com.example.project3.services.MeasurementsService;
import com.example.project3.util.MeasurementErrorResponse;
import com.example.project3.util.MeasurementNotCreatedException;
import com.example.project3.util.SensorValidator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;
    private final SensorValidator sensorValidator;

    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper,
                                  SensorValidator sensorValidator) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.sensorValidator = sensorValidator;
    }

    @GetMapping()
    public List<MeasurementDTO> index() {
        return measurementsService.findAll().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public MeasurementDTO findOne(@PathVariable("id") int id) {
        return convertToMeasurementDTO(measurementsService.findOne(id));
    }

    @PostMapping("/add")
    public void addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                               BindingResult bindingResult) {

        Measurement measurement = convertToMeasurement(measurementDTO);
        Sensor parentSensor = measurement.getSensor();

        sensorValidator.validateExist(parentSensor, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError e: errors) {
                errorMsg.append(e.getField())
                        .append(" - ")
                        .append(e.getDefaultMessage())
                        .append(";");
            }
            throw new MeasurementNotCreatedException(errorMsg.toString());
        }
        measurementsService.save(measurement);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleError(MeasurementNotCreatedException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
