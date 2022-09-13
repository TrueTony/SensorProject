package com.example.project3.dto;

import com.example.project3.models.Sensor;

import javax.validation.constraints.*;

public class MeasurementDTO {

    @NotNull(message = "Value не может быть пустым")
    @Min(value = -100, message = "Value не может быть меньше -100")
    @Max(value = 100, message = "Value не может быть больше 100")
    private float value;

    @NotNull(message = "Raining не может быть пустым")
    private boolean raining;

    @NotNull(message = "Sensor не может быть пустым")
    private Sensor sensor;

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
