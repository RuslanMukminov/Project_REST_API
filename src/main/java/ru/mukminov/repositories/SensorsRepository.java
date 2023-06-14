package ru.mukminov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mukminov.models.Sensor;

@Repository
public interface SensorsRepository extends JpaRepository<Sensor, Integer> {
}
