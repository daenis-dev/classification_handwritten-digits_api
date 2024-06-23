package com.greenpalm.classification.imageclassification;

import org.springframework.web.multipart.MultipartFile;

public interface ClassifyImage {

    String classify(MultipartFile multipartFile);
}
