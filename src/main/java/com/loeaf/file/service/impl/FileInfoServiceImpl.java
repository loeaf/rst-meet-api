package com.loeaf.file.service.impl;


import com.loeaf.common.misc.DateUtils;
import com.loeaf.common.misc.FileUtiles;
import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.file.domain.FileInfo;
import com.loeaf.file.persistence.FileInfoMapper;
import com.loeaf.file.persistence.FileInfoRepository;
import com.loeaf.file.service.FileInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 파일 처리 서비스
 * @author break8524
 */
@Service
@RequiredArgsConstructor
public class FileInfoServiceImpl
        extends ServiceImpl<FileInfoRepository, FileInfo, Long>
        implements FileInfoService {
    private final FileInfoRepository jpaRepo;

    @Value("${app.file.upload.path}")
    private String fileUploadPath = "";

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new FileInfo());
    }

    /**
     * MultipartStream을 파일로 저장후 리스트를 리턴합니다
     * @param files
     * @return
     */
    private List<FileInfo> getCPFilesByMultipart(MultipartFile[] files) {
        String Path = "";
        List<FileInfo> result = new ArrayList<>();
        for(MultipartFile mtf : files) {
            result.add(this.multipart2CPFileInfo(mtf));
        }
        return result;
    }

    /**
     * Multipart Stream을 파일로 변환 후 지구계획 객체로 리턴합니다
     * 파일명, 확장자, 파일경로 등이 확정됩니다.
     * @param multipartFile
     * @return
     */
    private FileInfo multipart2CPFileInfo(MultipartFile multipartFile) {
        FileInfo fi = new FileInfo();
        // 파일 정보
        String uploadDir = fileUploadPath;
        String originFilename = "";
        String extName = "";
        if(multipartFile.getOriginalFilename().equals("blob")) {
            originFilename = multipartFile.getOriginalFilename();
            extName = "png";
        } else {
            var splitInfo = multipartFile.getOriginalFilename().split(".");
            originFilename = splitInfo[0];
            extName = splitInfo[1];
        }
        Long size = multipartFile.getSize();

        // 서버에서 저장 할 파일 이름
        String saveFileName = DateUtils.getNowTime().toString();

        System.out.println("originFilename : " + originFilename);
        System.out.println("extensionName : " + extName);
        System.out.println("saveFileName : " + saveFileName);

        fi.setFileName(saveFileName);
        fi.setFilePath(uploadDir);
        fi.setOriginFileName(originFilename);
        fi.setFileExtention(extName);
        return fi;
    }


    /**
     *  Multipart Stream 전체를 파일로 저장 후 List 파일 정보를 리턴합니다
     * @param multipartFiles
     * @return
     */
    @Override
    public List<FileInfo> procCPFiles(MultipartFile[] multipartFiles) {
        var files = getCPFilesByMultipart(multipartFiles);
        for(int i = 0; i < multipartFiles.length; i++) {
            var file = files.get(i);
            try {
                FileUtiles.writeStreamFileByFullPath(multipartFiles[i], file.toString());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return files;
    }
}
