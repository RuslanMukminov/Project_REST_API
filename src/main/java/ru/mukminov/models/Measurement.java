package ru.mukminov.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Measurement")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @NotNull(message = "Value should not be empty")
    @DecimalMin(value = "-100.0", message = "Value should be between -100 and 100")
    @DecimalMax(value = "100.0", message = "Value should be between -100 and 100")
    private BigDecimal value;

    @Column(name = "raining")
    private boolean raining;

    @Column(name = "sensor_name")
    @NotEmpty(message = "Sensor name should not be empty")
    private String sensorName;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor owner;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Measurement() {
    }

    public Measurement(BigDecimal value, boolean raining, String sensorName) {
        this.value = value;
        this.raining = raining;
        this.sensorName = sensorName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public Sensor getOwner() {
        return owner;
    }

    public void setOwner(Sensor owner) {
        this.owner = owner;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
