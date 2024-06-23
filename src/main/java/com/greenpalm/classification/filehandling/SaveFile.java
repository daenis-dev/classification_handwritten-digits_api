package com.greenpalm.classification.filehandling;

import org.springframework.web.multipart.MultipartFile;

public interface SaveFile {

    String forMultipartAndTargetDirectory(MultipartFile multipartFile, String targetDirectory);
}
