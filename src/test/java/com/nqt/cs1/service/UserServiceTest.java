package com.nqt.cs1.service;

import com.nqt.cs1.service.imp.UploadService;
import jakarta.servlet.ServletContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

public class UserServiceTest {
    @Mock
    private ServletContext servletContext;

    @InjectMocks
    private UploadService uploadService;

    // Khởi tạo các mock
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHandleSaveUploadFile_EmptyFile() {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.isEmpty()).thenReturn(true);
        String result = uploadService.handleSaveUploadFile(mockFile, "uploads");
        assertEquals("Kết quả trả về phải là chuỗi rỗng khi file rỗng.", result, "");
    }

    @Test
    void testHandleSaveUploadFile_Success() throws IOException {
        MultipartFile file = mock(MultipartFile.class);
        when(file.isEmpty()).thenReturn(false);
        when(file.getOriginalFilename()).thenReturn("test.jpg");
        when(file.getBytes()).thenReturn("Test file content".getBytes());
        String result = uploadService.handleSaveUploadFile(file, "uploads");
        assertEquals("Tên file kết thúc bằng '-test.jpg'.", result.endsWith("-test.jpg"), true);
        String rootPath = Paths.get("src", "main", "resources", "static", "images").toFile().getAbsolutePath();
        File uploadedFile = new File(rootPath + File.separator + "uploads" + File.separator + result);
        assertEquals("", uploadedFile.exists(), true);
    }

    @Test
    void testHandleSaveUploadFile_Exception() throws IOException {
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.isEmpty()).thenReturn(false);
        when(mockFile.getOriginalFilename()).thenReturn("test.jpg");
        when(mockFile.getBytes()).thenThrow(IOException.class);
        String result = uploadService.handleSaveUploadFile(mockFile, "uploads");
        assertEquals("", result, "");
    }
}
