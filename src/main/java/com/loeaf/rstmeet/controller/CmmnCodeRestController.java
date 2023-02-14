package com.loeaf.rstmeet.controller;

import com.loeaf.file.service.FileInfoService;
import com.loeaf.rstmeet.service.CmmnCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/CmmnCode")
@Api(value = "CmmnCode")
public class CmmnCodeRestController {

    private CmmnCodeService service;
    private FileInfoService fileInfoServiceImpl;

    public CmmnCodeRestController(CmmnCodeService service, FileInfoService fileInfoServiceImpl) {
        this.service = service;
        this.fileInfoServiceImpl = fileInfoServiceImpl;
    }

    // get send fileinfo service
    @GetMapping("/getFood")
    @ApiOperation(value = "음식 목록")
    public ResponseEntity<Object> findFood(HttpServletRequest request) throws Exception {
        fileInfoServiceImpl.sendS3Files();
        return ResponseEntity.ok(null);
    }

    // find city by service
    @GetMapping("/getCity")
    @ApiOperation(value = "도시 목록")
    public ResponseEntity<Object> findCity(HttpServletRequest request) throws Exception {
        return ResponseEntity.ok(service.findCity());
    }
}