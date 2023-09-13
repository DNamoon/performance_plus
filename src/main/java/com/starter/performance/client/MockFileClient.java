package com.starter.performance.client;

import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MockFileClient implements FileClient {

    @Override
    public String upload(MultipartFile multipartFile) {
        return UUID.randomUUID().toString();
    }

    @Override
    public void delete(String fileUrl) {

    }
}
