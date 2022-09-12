package com.example.project3.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {

    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 3, max = 30, message = "Длина должна быть от 3 до 30 символов")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
