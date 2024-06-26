package it.imageclassification;

import com.greenpalm.classification.imageclassification.api.ClassifyImageAsDigit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
public class ClassifyImageAsDigitIT {

    @Value("${app.image-upload-file-path}")
    private String IMAGE_UPLOAD_FILE_PATH;

    private final String IMAGE_NAME = "test_image.jpg";

    @Autowired
    private ClassifyImageAsDigit classifyImageAsDigit;

    @Test
    void classifiesTheImageAsADigit() throws Exception {
        byte[] content = Files.readAllBytes(Paths.get("src/test/resources/" + IMAGE_NAME));
        MockMultipartFile image = new MockMultipartFile("file", IMAGE_NAME, MediaType.IMAGE_JPEG_VALUE, content);

        classifyImageAsDigit.classify(image);
    }

    @AfterEach
    void tearDown() throws Exception {
        Files.deleteIfExists(Paths.get(IMAGE_UPLOAD_FILE_PATH + "/" + IMAGE_NAME));
    }
}
