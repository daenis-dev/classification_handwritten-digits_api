package com.greenpalm.classification.integration;

import com.greenpalm.classification.integration.api.GetPredictedDigitForImageFilePath;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("it")
@Service
public class MockModelIntegrationService implements GetPredictedDigitForImageFilePath {

    @Override
    public String getPredictionFor(String theFilePath) {
        return "5";
    }
}
