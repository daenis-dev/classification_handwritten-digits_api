package com.greenpalm.classification.imageclassification;

import com.greenpalm.classification.filehandling.api.SaveFile;
import com.greenpalm.classification.imageclassification.api.ClassifyImageAsDigit;
import com.greenpalm.classification.imageclassification.api.ImageClassification;
import com.greenpalm.classification.integration.api.GetPredictedDigitForImageFilePath;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
class ImageClassificationService implements ClassifyImageAsDigit {

    @Value("${app.image-upload-file-path}")
    private String IMAGE_UPLOAD_FILE_PATH;

    private final SaveFile saveFile;
    private final GetPredictedDigitForImageFilePath getPredictedDigitForImageFilePath;

    @Override
    public ImageClassification classify(MultipartFile multipartFile) {
        String theFilePath = saveFile.forMultipartAndTargetDirectory(multipartFile, IMAGE_UPLOAD_FILE_PATH);
        return new ImageClassification(getPredictedDigitForImageFilePath.getPredictionFor(theFilePath));
    }
}
