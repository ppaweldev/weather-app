package com.example.weatherapp.service;

import com.example.weatherapp.provider.TemperatureProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LoadDataService {

    private final AverageTemperatureService service;
    private final TemperatureProvider temperatureProvider;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        service.save(service.createAverageTemperatureList(temperatureProvider.getTemperatures()));
    }

    @Scheduled(cron = "0 0 * * * *")
    public void refreshData() {
        loadData();
    }
}
