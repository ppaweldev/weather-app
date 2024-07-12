package com.example.weatherapp.service;

import com.example.weatherapp.model.AverageTemperatureByCity;
import com.example.weatherapp.provider.TemperatureProvider;

import java.util.List;

public interface AverageTemperatureService {

    List<AverageTemperatureByCity>  createAverageTemperatureList(List<TemperatureProvider.Temperature> temperatureListSortedByCity);
    void save(List<AverageTemperatureByCity> averageTemperatureByCityList);
    List<AverageTemperatureByCity> getByCity(String city);

}
