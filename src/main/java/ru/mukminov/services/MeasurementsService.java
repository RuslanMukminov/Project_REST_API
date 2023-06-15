package ru.mukminov.services;

import org.springframework.stereotype.Service;
import ru.mukminov.models.Measurement;
import ru.mukminov.models.Sensor;
import ru.mukminov.repositories.MeasurementsRepository;

import java.time.LocalDateTime;

@Service
public class MeasurementsService {
    private final MeasurementsRepository measurementsRepository;

    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    private void enrichMeasurement(Measurement measurement, Sensor sensor) {
        measurement.setOwner(sensor);
        measurement.setCreatedAt(LocalDateTime.now());
    }

    public void add(Measurement measurement, Sensor sensor) {
        enrichMeasurement(measurement, sensor);
        measurementsRepository.save(measurement);
    }
}
