package com.loeaf.rstmeet.controller;

import com.loeaf.rstmeet.service.MenuService;
import com.loeaf.rstmeet.service.ReViewService;
import com.loeaf.rstmeet.service.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/DefaultData")
@Api(value = "DefaultData")
public class DefaultDataRestController {

    private RestaurantService restaurantService;
    private ReViewService reViewService;
    private MenuService menuService;

    public DefaultDataRestController(RestaurantService service,
                                     ReViewService reViewService,
                                     MenuService menuService) {
        this.restaurantService = service;
        this.reViewService = reViewService;
        this.menuService = menuService;
    }

    @GetMapping("/restaurantBulkInsert")
    @ApiOperation(value = "등록")
    public ResponseEntity<Object> restaurantBulkInsert(HttpServletRequest request) throws Exception {
        this.restaurantService.registBulkByCSV();
        return null;
    }

    @GetMapping("/reviewBulkInsert")
    @ApiOperation(value = "등록")
    public ResponseEntity<Object> reviewBulkInsert(HttpServletRequest request) throws Exception {
        this.reViewService.registBulkByCSV();
        return null;
    }

    @GetMapping("/menuBulkInsert")
    @ApiOperation(value = "등록")
    public ResponseEntity<Object> menuBulkInsert(HttpServletRequest request) throws Exception {
        this.menuService.registBulkByCSV();
        return null;
    }
}