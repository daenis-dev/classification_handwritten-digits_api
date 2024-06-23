package com.greenpalm.classification.imageclassification;

import com.greenpalm.classification.filehandling.api.SaveFile;
import com.greenpalm.classification.imageclassification.api.ClassifyImageAsDigit;
import com.greenpalm.classification.integration.api.GetPredictedDigitForImageFilePath;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
class ImageClassificationService implements ClassifyImageAsDigit {

    private final SaveFile saveFile;
    private final GetPredictedDigitForImageFilePath getPredictedDigitForImageFilePath;

    @Override
    public String classify(MultipartFile multipartFile) {
        String theFilePath = saveFile.forMultipartAndTargetDirectory(multipartFile, "/Users/dkala/projects/modmat/modmat-api/src/main/resources");
        return getPredictedDigitForImageFilePath.getPredictionFor(theFilePath);
    }
}
