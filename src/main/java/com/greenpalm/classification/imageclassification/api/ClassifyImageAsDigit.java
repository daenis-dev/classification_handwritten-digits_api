package com.greenpalm.classification.imageclassification.api;

import org.springframework.web.multipart.MultipartFile;

public interface ClassifyImageAsDigit {

    String classify(MultipartFile multipartFile);
}
