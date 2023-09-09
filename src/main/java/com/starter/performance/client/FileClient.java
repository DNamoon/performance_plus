package com.starter.performance.client;

import org.springframework.web.multipart.MultipartFile;

public interface FileClient {

    String upload(MultipartFile multipartFile);

    void delete(String fileUrl);
}
