package com.example.project3.dto;

import com.example.project3.models.Sensor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class MeasurementDTO {

    @NotEmpty(message = "Value не может быть пустым")
    @Min(value = -100, message = "Value не может быть меньше -100")
    @Max(value = 100, message = "Value не может быть больше 100")
    private float value;

    @NotEmpty(message = "Raining не может быть пустым")
    private boolean raining;

    @NotEmpty(message = "Sensor не может быть пустым")
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
