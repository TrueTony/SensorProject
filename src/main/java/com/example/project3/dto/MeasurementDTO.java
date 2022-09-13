package com.example.project3.dto;

import javax.validation.constraints.*;

public class MeasurementDTO {

    @NotNull(message = "Value не может быть пустым")
    @Min(value = -100, message = "Value не может быть меньше -100")
    @Max(value = 100, message = "Value не может быть больше 100")
    private float value;

    @NotNull(message = "Raining не может быть пустым")
    private boolean raining;

    @NotNull(message = "Sensor не может быть пустым")
    private SensorDTO sensor;

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
