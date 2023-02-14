package com.loeaf.rstmeet.controller;

import com.loeaf.rstmeet.dto.params.RestaurantParam;
import com.loeaf.rstmeet.service.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/Restaurant")
@Api(value = "Restaurant")
public class RestaurantRestController {

    private RestaurantService service;

    public RestaurantRestController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping()
    @ApiOperation(value = "기본 전체목록")
    public ResponseEntity<Object> findAll(HttpServletRequest request, RestaurantParam params) throws Exception {
        if (params.getId() != null) {
            return ResponseEntity.ok(service.findById(params.getId()));
        } else {
            return ResponseEntity.ok(service.findAll());
        }
    }
}