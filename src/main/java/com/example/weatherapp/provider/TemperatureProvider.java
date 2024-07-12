package com.example.weatherapp.provider;

import com.example.weatherapp.exception.FileReadingException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class TemperatureProvider {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String EXAMPLE_FILE_CSV = "file/example_file.csv";

    public List<Temperature> getTemperatures() {
        List<Temperature> records = new ArrayList<>();
        ClassPathResource resource = new ClassPathResource(EXAMPLE_FILE_CSV);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 3) {
                    String city = data[0];
                    LocalDateTime dateTime = LocalDateTime.parse(data[1], DateTimeFormatter.ofPattern(DATE_TIME_FORMAT));
                    BigDecimal temp = new BigDecimal(data[2]);
                    records.add(new Temperature(city, dateTime, temp));
                }
            }
            return records;
        } catch (Exception e) {
            throw new FileReadingException(e.getMessage());
        }
    }

    public record Temperature(
            String city,
            LocalDateTime dateTime,
            BigDecimal totalTemperature) {
    }
}
