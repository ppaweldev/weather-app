package com.example.weatherapp.service;

import com.example.weatherapp.model.AverageTemperatureByCity;
import com.example.weatherapp.provider.TemperatureProvider;
import com.example.weatherapp.repository.AverageTemperatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AverageTemperatureServiceImpl implements AverageTemperatureService {

    private final AverageTemperatureRepository repository;

    @Override
    public List<AverageTemperatureByCity> createAverageTemperatureList(List<TemperatureProvider.Temperature> temperatureListSortedByCity) {
        return temperatureListSortedByCity.stream()
                .collect(Collectors.groupingBy(
                        temp -> new AbstractMap.SimpleEntry<>(temp.city(), temp.dateTime().getYear()),
                        Collectors.mapping(TemperatureProvider.Temperature::totalTemperature, Collectors.toList())))
                .entrySet().stream()
                .map(entry -> {
                    String city = entry.getKey().getKey();
                    int year = entry.getKey().getValue();
                    List<BigDecimal> temperatures = entry.getValue();
                    BigDecimal average = temperatures.stream()
                            .reduce(BigDecimal.ZERO, BigDecimal::add)
                            .divide(BigDecimal.valueOf(temperatures.size()), 1, 0);
                    return new AverageTemperatureByCity(city, year, average);
                })
                .toList();
    }

    @Override
    public void save(List<AverageTemperatureByCity> averageTemperatureByCityList) {
        repository.save(averageTemperatureByCityList);
    }

    @Override
    public List<AverageTemperatureByCity> getByCity(String city) {
        return repository.getByCity(city);
    }
}
