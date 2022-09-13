package com.example.project3.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "measurement")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull(message = "Value не может быть пустым")
    @Min(value = -100, message = "Value не может быть меньше -100")
    @Max(value = 100, message = "Value не может быть больше 100")
    @Column(name = "value")
    private float value;

    @NotNull(message = "Raining не может быть пустым")
    @Column(name = "raining")
    private boolean raining;

    @NotNull(message = "Sensor не может быть пустым")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Measurement() {
    }

    public Measurement(float value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
