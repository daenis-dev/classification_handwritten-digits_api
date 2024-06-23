package com.greenpalm.classification.imageclassification.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ImageClassificationController {

    private final ClassifyImageAsDigit classifyImageAsDigit;

    @GetMapping("/v1/classify-image-as-digit")
    public ResponseEntity<String> getDigitInImage(@RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(classifyImageAsDigit.classify(image));
    }
}
