package ru.mukminov.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.mukminov.dto.MeasurementDTO;
import ru.mukminov.models.Measurement;
import ru.mukminov.models.Sensor;
import ru.mukminov.services.MeasurementsService;
import ru.mukminov.services.SensorsService;
import ru.mukminov.util.MeasurementNotAddedException;
import ru.mukminov.util.ErrorResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final ModelMapper modelMapper;
    private final SensorsService sensorsService;

    public MeasurementsController(MeasurementsService measurementsService, ModelMapper modelMapper, SensorsService sensorsService) {
        this.measurementsService = measurementsService;
        this.modelMapper = modelMapper;
        this.sensorsService = sensorsService;
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new MeasurementNotAddedException(errorMsg.toString());
        }

        Optional<Sensor> sensorOptional = sensorsService.findSensor(measurementDTO.getSensor().getName());

        if (sensorOptional.isEmpty()) {
            throw new MeasurementNotAddedException("Sensor is not registered!");
        }

        Measurement measurement = convertToMeasurement(measurementDTO);
        measurementsService.add(measurement, sensorOptional.get());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(MeasurementNotAddedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping()
    public List<MeasurementDTO> getMeasurements() {
        return measurementsService.findAll()
                .stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount() {
        return measurementsService.getRainyDaysCount();
    }
}
