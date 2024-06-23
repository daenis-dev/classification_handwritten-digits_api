package com.greenpalm.classification.integration;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

@Service
class PredictionService implements GetPrediction {

    @Override
    public String getPredictionFor(String theFilePath) {
        WebClient webClient = WebClient
                .builder()
                .baseUrl("https://localhost:5000/predict_value")
                .build();

        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("file-path", theFilePath)
                        .build())
                .exchangeToMono(response -> response.bodyToMono(String.class))
                .block(Duration.ofSeconds(10));
    }
}
