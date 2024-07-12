package com.example.weatherapp.repository;

import com.example.weatherapp.model.AverageTemperatureByCity;

import java.util.List;

public interface AverageTemperatureRepository {

    void save(List<AverageTemperatureByCity> averageTemperatureByCityList);
    List<AverageTemperatureByCity> getByCity(String city);
}
