package com.example.weatherapp.provider

import spock.lang.Specification

class TemperatureProviderTest extends Specification {

    def temperatureProvider = new TemperatureProvider()

    def "getTemperatures"() {
        given:
        def expectedSize = 8
        when:
        def result = temperatureProvider.getTemperatures()
        then:
        result.size() == expectedSize
    }
}
