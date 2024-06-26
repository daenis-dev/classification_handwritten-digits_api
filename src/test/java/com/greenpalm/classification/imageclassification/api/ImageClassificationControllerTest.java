package com.greenpalm.classification.imageclassification.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ImageClassificationControllerTest {

    @Mock
    private ClassifyImageAsDigit classifyImageAsDigit;

    private MockMvc mockMvc;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new ImageClassificationController(classifyImageAsDigit))
                .build();
    }

    @Test
    void classifiesTheImageAsADigit() throws Exception {
        MockMultipartFile mockFile = new MockMultipartFile(
                "image",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Test content".getBytes()
        );

        mockMvc.perform(multipart("/v1/classify-image-as-digit")
                        .file(mockFile))
                .andExpect(status().isOk());
    }
}