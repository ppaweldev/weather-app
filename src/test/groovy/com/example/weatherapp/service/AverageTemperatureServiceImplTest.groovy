package com.example.weatherapp.service

import com.example.weatherapp.model.AverageTemperatureByCity
import com.example.weatherapp.provider.TemperatureProvider
import com.example.weatherapp.repository.AverageTemperatureRepository
import spock.lang.Specification

import java.time.LocalDateTime

class AverageTemperatureServiceImplTest extends Specification {

    def repository = Mock(AverageTemperatureRepository.class)
    def averageTemperatureService = new AverageTemperatureServiceImpl(repository)

    def "should createAverageTemperatureList"() {
        given:
        def temperatures = [new TemperatureProvider.Temperature('Warszawa', LocalDateTime.of(2022,12, 23, 0, 0,0 ,0), new BigDecimal('9.4')),
                            new TemperatureProvider.Temperature('Warszawa', LocalDateTime.of(2023,12, 23, 0, 0,0 ,0), new BigDecimal('9.5'))]
        def expected = [new AverageTemperatureByCity('Warszawa', 2022, new BigDecimal('9.4')),
                        new AverageTemperatureByCity('Warszawa', 2023, new BigDecimal('9.5'))]
        when:
        def result = averageTemperatureService.createAverageTemperatureList(temperatures)
        then:
        result == expected
    }

    def "should invoke save"() {
        given:
        def list = [new AverageTemperatureByCity('Warszawa', 2022, new BigDecimal('9.4')),
         new AverageTemperatureByCity('Warszawa', 2023, new BigDecimal('9.5'))]
        when:
        averageTemperatureService.save(list)
        then:
        1 * repository.save(list)
    }

    def "should invoke getByCity"() {
        def city = 'city'
        when:
        averageTemperatureService.getByCity(city)
        then:
        1 * repository.getByCity(city)
    }
}
