package com.loeaf.common.misc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Slf4j
public class FileUtiles {
    /**
     * 파일을 Object 형태로 리턴
     * @param
     * @return
     */
    protected Object file2Object(String filePath) {
        return file2Object(new File(filePath));
    }


    /**
     * @param path 파일의  fullpath
     * @return
     */
    protected Object file2Object(Path path) {
        return file2Object(path.toFile());
    }

    /**
     * object로 변환
     * @param path 파일 경로
     * @param filename 파일명
     * @return
     */
    protected Object file2Object(Path path, String filename) {
        return file2Object(path.resolve(filename));
    }

    /**
     * file을 Object로 변환
     * @param file 파일
     * @return
     */
    protected Object file2Object(File file) {
        try {
            try(InputStream targetStream = new FileInputStream(file)){
                return new ObjectMapper().readValue(targetStream, Object.class);
            }
        } catch (IOException e) {
            log.error("{}",e);
        }

        //
        return null;
    }


    /**
     * image file을 base64 문자열로 변환
     * @param fullname
     * @return
     */
    protected String fileToBase64String(String fullname) {
        if(Utils.isEmpty(fullname)) {
            log.warn("<< empty fullname");
            return "";
        }

        //
        return fileToBase64String(Paths.get(fullname));
    }



    /**
     * image file을 base64 문자열로 변환
     * @param filepath
     * @return
     */
    protected String fileToBase64String(Path filepath) {
        if(!Files.exists(filepath)) {
            log.warn("<< not exists file {}", filepath);
            return "";
        }

        //
        return fileToBase64String(filepath.toFile());
    }

    /**
     * image file을 base64 문자열로 변환
     * @param file
     * @return
     */
    protected String fileToBase64String(File file) {
        if(null == file) {
            log.warn("<< null file");
            return "";
        }

        if(!file.exists()) {
            log.warn("<< not exists file {}", file);
            return "";
        }

        //
        try {
            byte[] bytes = FileUtils.readFileToByteArray(file);
            return Base64.getEncoder().encodeToString(bytes);
        } catch (IOException e) {
            log.error("{}", e);
        }

        //
        return "";
    }

    /**
     * 파일, 디렉터리 모두 삭제 가능
     * @param path 경로
     * @throws IOException  예외
     */
    public static void forceDelete(String path) throws IOException {

        if(!Files.exists(Paths.get(path))) {
            return;
        }

        //
        FileUtils.forceDelete(Paths.get(path).toFile());
    }

    /**
     * multipart Stream을 파일로 저장합니다
     * @param multipartFile
     * @param fullPath
     * @throws IOException
     */
    public static void writeStreamFileByFullPath(MultipartFile multipartFile, String fullPath) throws IOException{
        byte[] data = multipartFile.getBytes();
        FileOutputStream fos = new FileOutputStream(fullPath);
        fos.write(data);
        fos.close();
    }

}
