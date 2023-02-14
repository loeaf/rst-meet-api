package com.loeaf.file.service;

import com.loeaf.common.misc.Service;
import com.loeaf.file.domain.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileInfoService extends Service<FileInfo, Long> {
    List<FileInfo> procCPFiles(MultipartFile[] multipartFiles);
    void sendS3Files() throws IOException;
}
