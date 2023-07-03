package ru.mukminov.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mukminov.dto.SensorDTO;
import ru.mukminov.models.Sensor;
import ru.mukminov.services.SensorsService;
import ru.mukminov.util.ErrorResponse;
import ru.mukminov.util.ErrorsMsgUtil;
import ru.mukminov.util.SensorNotCreatedException;

import javax.validation.Valid;

@RestController
@RequestMapping("/sensors")
public class SensorsController {
    private final SensorsService sensorsService;
    private final ModelMapper modelMapper;

    public SensorsController(SensorsService sensorsService,
                             ModelMapper modelMapper) {
        this.sensorsService = sensorsService;
        this.modelMapper = modelMapper;
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new SensorNotCreatedException(ErrorsMsgUtil.getErrorsMsg(bindingResult));
        }

        if (sensorsService.findSensor(sensorDTO.getName()).isPresent()) {
            throw new SensorNotCreatedException("Such a sensor already exists!");
        }

        sensorsService.save(convertToSensor(sensorDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotCreatedException e) {
        ErrorResponse response = new ErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
