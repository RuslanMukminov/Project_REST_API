package ru.mukminov.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class MeasurementDTO {
    @NotEmpty(message = "Value should not be empty")
    @Size(min = -100, max = 100, message = "Value should be between -100 and 100")
    private double value;

    @NotEmpty(message = "Field 'raining' should not be empty")
    private boolean raining;

    @NotEmpty(message = "Sensor name should not be empty")
    private String sensorName;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }
}
