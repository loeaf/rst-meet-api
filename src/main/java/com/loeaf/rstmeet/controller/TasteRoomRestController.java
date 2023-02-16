package com.loeaf.rstmeet.controller;

import com.loeaf.rstmeet.dto.ResResult;
import com.loeaf.rstmeet.dto.params.TasteRoomParam;
import com.loeaf.rstmeet.model.Restaurant;
import com.loeaf.rstmeet.model.TasteRoom;
import com.loeaf.rstmeet.service.RestaurantService;
import com.loeaf.rstmeet.service.TasteRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/TasteRoom")
@Api(value = "TasteRoom")
public class TasteRoomRestController {

    private TasteRoomService service;
    private RestaurantService restaurantService;

    public TasteRoomRestController(TasteRoomService service, RestaurantService restaurantService) {
        this.service = service;
        this.restaurantService = restaurantService;
    }

    @GetMapping("")
    @ApiOperation(value = "기본 전체목록")
    public ResponseEntity<ResResult> findAll(HttpServletRequest request, Pageable pageable, @RequestParam String tasteRoomUuid) throws Exception {
        ResResult resResult = new ResResult();
        Restaurant restaurant = this.restaurantService.findById(tasteRoomUuid);
        resResult.setData(service.findByRestaurantId(restaurant));
        return ResponseEntity.ok(resResult);
    }

    @PostMapping()
    @ApiOperation(value = "등록")
    public ResponseEntity<TasteRoom> regist(HttpServletRequest request, @RequestBody TasteRoomParam tasteRoomParam) throws Exception {
        TasteRoom tasteRoom = new TasteRoom();
        tasteRoom.setId(UUID.randomUUID().toString());
        tasteRoom.setContent(tasteRoomParam.getContent());
        Restaurant restaurant = this.restaurantService.findById(tasteRoomParam.getRestaurantId());
        tasteRoom.setRestaurantId(restaurant);
        tasteRoom.setCreateDate(new Date());
        tasteRoom.setPeopleNum(tasteRoomParam.getPeopleNum());
        tasteRoom.setMeetPaymentType(tasteRoomParam.getMeetPaymentType());
        return ResponseEntity.ok(this.service.regist(tasteRoom));
    }
}