package com.example.weatherapp.repository;

import com.example.weatherapp.exception.CityNotFoundException;
import com.example.weatherapp.model.AverageTemperatureByCity;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class AverageTemperatureRepositoryInMemory implements AverageTemperatureRepository {

    private Map<String, List<AverageTemperatureByCity>> averageTemperatureMap = new ConcurrentHashMap<>();

    @Override
    public void save(List<AverageTemperatureByCity> averageTemperatureByCityList) {
        averageTemperatureMap = averageTemperatureByCityList.stream()
                .sorted(Comparator.comparing(AverageTemperatureByCity::city)
                        .thenComparing(AverageTemperatureByCity::year))
                .collect(Collectors.groupingBy(AverageTemperatureByCity::city));
    }

    @Override
    public List<AverageTemperatureByCity> getByCity(String city) {
        return averageTemperatureMap.computeIfAbsent(city, key -> {
            throw new CityNotFoundException(city);
        });
    }
}
