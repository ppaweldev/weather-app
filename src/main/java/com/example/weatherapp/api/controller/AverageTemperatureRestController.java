package com.example.weatherapp.api.controller;

import com.example.weatherapp.api.dto.AverageTemperatureByCityDto;
import com.example.weatherapp.service.AverageTemperatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.weatherapp.mapper.AverageTemperatureMapper.toDto;

@RestController
@RequestMapping("/average/temperature")
@RequiredArgsConstructor
class AverageTemperatureRestController {
    private final AverageTemperatureService temperatureService;

    @GetMapping("/{city}")
    List<AverageTemperatureByCityDto> getYearlyAverageTemperatureList(@PathVariable String city) {
        return toDto(temperatureService.getByCity(city));
    }
}

