package com.greenpalm.classification.filehandling;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
class FileService implements SaveFile {

    @Override
    public String forMultipartAndTargetDirectory(MultipartFile multipartFile, String targetDirectory) {
        try {
            if (directoryDoesNotExistWithPath(targetDirectory)) {
                createDirectory(targetDirectory);
            }
            File file = new File(targetDirectory + "/" + multipartFile.getOriginalFilename());
            if (file.exists()) {
                throw new RuntimeException("A file with this name has already been uploaded");
            }
            file.createNewFile();
            try (OutputStream os = new FileOutputStream(file)) {
                os.write(multipartFile.getBytes());
            }
            return file.getPath();
        } catch (IOException ex) {
            throw new RuntimeException("Error occurred while uploading image", ex);
        }
    }

    private boolean directoryDoesNotExistWithPath(String directory) {
        return !Files.exists(Paths.get(directory));
    }

    private void createDirectory(String directory) {
        new File(directory).mkdir();
    }
}
