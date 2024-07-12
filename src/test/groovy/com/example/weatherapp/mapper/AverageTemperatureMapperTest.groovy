package com.example.weatherapp.mapper

import com.example.weatherapp.api.dto.AverageTemperatureByCityDto
import com.example.weatherapp.model.AverageTemperatureByCity
import spock.lang.Specification

import static com.example.weatherapp.mapper.AverageTemperatureMapper.toDto;

class AverageTemperatureMapperTest extends Specification {

    def "toDto"() {
        given:
        def averageByCity = [new AverageTemperatureByCity('Warszawa', 2022, new BigDecimal('9.4'))]
        def expected = [new AverageTemperatureByCityDto(2022, new BigDecimal('9.4'))]
        when:
        def result = toDto(averageByCity)
        then:
        result == expected
    }
}
