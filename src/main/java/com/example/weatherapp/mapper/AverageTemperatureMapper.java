package com.example.weatherapp.mapper;

import com.example.weatherapp.api.dto.AverageTemperatureByCityDto;
import com.example.weatherapp.model.AverageTemperatureByCity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class AverageTemperatureMapper {

    public static List<AverageTemperatureByCityDto> toDto(List<AverageTemperatureByCity> averageTemperatureByCityList) {
        return averageTemperatureByCityList.stream()
                .map(averageTemperatureByCity -> new AverageTemperatureByCityDto(averageTemperatureByCity.year(), averageTemperatureByCity.averageTemp()))
                .toList();
    }
}
