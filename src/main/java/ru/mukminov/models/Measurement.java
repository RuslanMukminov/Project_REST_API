package ru.mukminov.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "Measurement")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @NotEmpty(message = "Value should not be empty")
    @Size(min = -100, max = 100, message = "Value should be between -100 and 100")
    private double value;

    @Column(name = "raining")
    @NotEmpty(message = "Field 'raining' should not be empty")
    private boolean raining;

    @Column(name = "sensor_name")
    @NotEmpty(message = "Sensor name should not be empty")
    private String sensorName;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor owner;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
