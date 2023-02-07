package com.loeaf.rstmeet.controller;

import com.loeaf.rstmeet.service.ChattingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Chatting")
@Api(value = "Chatting")
public class ChattingRestController {

    private ChattingService service;

    public ChattingRestController(ChattingService service) {
        this.service = service;
    }

    @GetMapping("")
    @ApiOperation(value = "기본 전체목록")
    public ResponseEntity<Object> findAll(HttpServletRequest request, Pageable pageable) throws Exception {
        return null;
    }

    @PostMapping()
    @ApiOperation(value = "등록")
    public ResponseEntity<Object> regist(HttpServletRequest request, @RequestBody Object dto) throws Exception {
//        service.regist(dto);
        return null;
    }
}