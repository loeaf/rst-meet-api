package com.loeaf.rstmeet.controller;

import com.loeaf.rstmeet.dto.ResResult;
import com.loeaf.rstmeet.dto.params.RestaurantParam;
import com.loeaf.rstmeet.model.Restaurant;
import com.loeaf.rstmeet.service.RestaurantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public ResponseEntity<ResResult> findAll(HttpServletRequest request, RestaurantParam params) throws Exception {
        ResResult resResult = new ResResult();
        if (params.getId() != null) {
            Restaurant restaurants = service.findById(params.getId());
            resResult.setData(restaurants);
            return ResponseEntity.ok(resResult);
        } else {
            List<Restaurant> restaurantList = service.findRestaurant(null);
            resResult.setData(restaurantList);
            return ResponseEntity.ok(resResult);
        }
    }
}