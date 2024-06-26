package com.greenpalm.classification.filehandling;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FileHandlingServiceTest {

    private final String FILE_NAME = "test.txt";
    private final String TARGET_DIRECTORY = "src\\test\\resources\\test-images";

    private MockMultipartFile file;

    private FileHandlingService fileHandlingService;

    @BeforeEach
    void init() throws Exception {
        byte[] content = Files.readAllBytes(Paths.get( "src\\test\\resources\\" + FILE_NAME));
        file = new MockMultipartFile("file", FILE_NAME, MediaType.TEXT_PLAIN_VALUE, content);

        fileHandlingService = new FileHandlingService();
    }

    @Test
    void createsTheNewFileAndTheTargetDirectory() {
        String theReturnedPath = fileHandlingService.forMultipartAndTargetDirectory(file, TARGET_DIRECTORY);

        assertThat(theReturnedPath).isEqualTo(TARGET_DIRECTORY + '\\' + FILE_NAME);
        assertThat(theFileExistsWithFilePath(TARGET_DIRECTORY + '\\' + FILE_NAME));
    }

    @Test
    void createsTheNewFileUnderTheExistingTargetDirectory() {
        createTheTargetDirectory();

        String theReturnedPath = fileHandlingService.forMultipartAndTargetDirectory(file, TARGET_DIRECTORY);

        assertThat(theReturnedPath).isEqualTo(TARGET_DIRECTORY + '\\' + FILE_NAME);
        assertThat(theFileExistsWithFilePath(TARGET_DIRECTORY + '\\' + FILE_NAME));
    }

    private void createTheTargetDirectory() {
        new File(TARGET_DIRECTORY).mkdir();
    }

    private boolean theFileExistsWithFilePath(String filePath) {
        return Files.exists(Paths.get(filePath));
    }

    @Test
    void doesNotCreateANewFileIfOneExistsWithTheSameName() throws Exception {
        createTheFile();

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> fileHandlingService.forMultipartAndTargetDirectory(file, TARGET_DIRECTORY));

        assertThat(thrown.getMessage()).isEqualTo("A file with this name has already been uploaded");
    }

    private void createTheFile() throws Exception {
        new File(TARGET_DIRECTORY).mkdir();
        new File(TARGET_DIRECTORY + "\\" + FILE_NAME).createNewFile();
    }

    @AfterEach
    void teardown() throws Exception {
        Files.deleteIfExists(Paths.get(TARGET_DIRECTORY + "/" + FILE_NAME));
    }
}