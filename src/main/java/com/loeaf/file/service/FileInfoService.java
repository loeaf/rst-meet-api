package com.loeaf.file.service;

import com.loeaf.common.misc.Service;
import com.loeaf.file.domain.FileInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileInfoService extends Service<FileInfo, Long> {
    List<FileInfo> procCPFiles(MultipartFile[] multipartFiles);
}
