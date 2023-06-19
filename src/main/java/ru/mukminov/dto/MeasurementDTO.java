package ru.mukminov.dto;

import ru.mukminov.models.Sensor;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class MeasurementDTO {
    @NotNull(message = "Value should not be empty")
    @DecimalMin(value = "-100.0", message = "Value should be between -100 and 100")
    @DecimalMax(value = "100.0", message = "Value should be between -100 and 100")
    private BigDecimal value;

    @NotNull(message = "Field 'raining' should not be empty")
    private boolean raining;

    private Sensor sensor;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
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
