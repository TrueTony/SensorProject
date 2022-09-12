package com.example.project3.controllers;

import com.example.project3.dto.MeasurementDTO;
import com.example.project3.models.Measurement;
import com.example.project3.services.MeasurementsService;
import org.modelmapper.ModelMapper;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;

    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
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
    public void addMeasurement(@RequestBody @Valid Measurement measurement,
                               BindingResult bindingResult) {

    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }
}
