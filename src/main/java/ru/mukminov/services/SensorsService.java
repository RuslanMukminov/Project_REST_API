package ru.mukminov.services;

import org.springframework.stereotype.Service;
import ru.mukminov.models.Sensor;
import ru.mukminov.repositories.SensorsRepository;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class SensorsService {
    private final SensorsRepository sensorsRepository;

    public SensorsService(SensorsRepository sensorsRepository) {
        this.sensorsRepository = sensorsRepository;
    }

    private void enrichSensor(Sensor sensor) {
        sensor.setCreatedAT(LocalDateTime.now());
    }

    @Transactional
    public void save(Sensor sensor) {
        enrichSensor(sensor);
        sensorsRepository.save(sensor);
    }

    public Optional<Sensor> findSensor(String name) {
        return sensorsRepository.findByName(name);
    }
}
