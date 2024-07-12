package com.example.weatherapp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class AverageTemperatureRestControllerIt extends Specification {

    static String AVERAGE_TEMP_PATH = "/average/temperature/%s"

    @Autowired
    private MockMvc mockMvc

    def "should return status OK for GET request"() {
        when: "sending GET request"
        def result = mockMvc.perform(MockMvcRequestBuilders.get(AVERAGE_TEMP_PATH.formatted(city)))
        then: "response is OK"
        result.andExpect(status().isOk())
                .andExpect(jsonPath("\$[*].year").exists())
                .andExpect(jsonPath("\$[*].averageTemperature").exists())
        where:
        city << ['Warszawa', 'Gdańsk', 'Wrocław']
    }

    def "should return Not Found for GET request"() {
        when: "sending GET request"
        def result = mockMvc.perform(MockMvcRequestBuilders.get(AVERAGE_TEMP_PATH.formatted(city)))
        then: "response is Not Found"
        result.andExpect(status().isNotFound())
                .andExpect(jsonPath("\$.message")
                        .value("There is no data for city: %s".formatted(city)))
        where:
        city << ['invalid1', 'invalid2', 'invalid3']
    }
}

