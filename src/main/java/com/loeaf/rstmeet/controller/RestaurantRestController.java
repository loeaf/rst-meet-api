package com.loeaf.rstmeet.controller;

import com.loeaf.rstmeet.service.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Restaurant")
@Api(value = "Restaurant")
public class RestaurantRestController {

    private RestaurantService service;

    public RestaurantRestController(RestaurantService service) {
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
        return null;
    }

    // bulk insert
    @GetMapping("/TEST")
    @ApiOperation(value = "등록")
    public ResponseEntity<Object> bulk(HttpServletRequest request) throws Exception {
        this.service.registBulkByCSV();
        return null;
    }
}