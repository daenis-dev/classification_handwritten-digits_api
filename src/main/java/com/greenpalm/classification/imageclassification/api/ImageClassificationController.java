package com.greenpalm.classification.imageclassification.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ImageClassificationController {

    private final ClassifyImageAsDigit classifyImageAsDigit;

    @PostMapping("/v1/classify-image-as-digit")
    public ResponseEntity<ImageClassification> classifyImageAsDigit(@RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok(classifyImageAsDigit.classify(image));
    }
}
