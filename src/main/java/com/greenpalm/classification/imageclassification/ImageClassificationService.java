package com.greenpalm.classification.imageclassification;

import com.greenpalm.classification.filehandling.SaveFile;
import com.greenpalm.classification.integration.GetPrediction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
class ImageClassificationService implements ClassifyImage {

    private final SaveFile saveFile;
    private final GetPrediction getPrediction;

    @Override
    public String classify(MultipartFile multipartFile) {
        String theFilePath = saveFile.forMultipartAndTargetDirectory(multipartFile, "/Users/dkala/projects/modmat/modmat-api/src/main/resources");
        return getPrediction.getPredictionFor(theFilePath);
    }
}
