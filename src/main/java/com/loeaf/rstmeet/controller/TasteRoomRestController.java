package com.loeaf.rstmeet.controller;

import com.loeaf.rstmeet.dto.ResResult;
import com.loeaf.rstmeet.dto.params.TasteRoomParam;
import com.loeaf.rstmeet.model.Restaurant;
import com.loeaf.rstmeet.model.TasteRoom;
import com.loeaf.rstmeet.model.TasteRoomMember;
import com.loeaf.rstmeet.service.RestaurantService;
import com.loeaf.rstmeet.service.TasteRoomMemberService;
import com.loeaf.rstmeet.service.TasteRoomService;
import com.loeaf.siginin.dto.UserToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/TasteRoom")
@Api(value = "TasteRoom")
public class TasteRoomRestController {

    private TasteRoomService service;
    private RestaurantService restaurantService;
    private TasteRoomMemberService tasteRoomMemberService;
    @Autowired
    private UserToken userToken;

    public TasteRoomRestController(TasteRoomService service, RestaurantService restaurantService, TasteRoomMemberService tasteRoomMemberService) {
        this.service = service;
        this.restaurantService = restaurantService;
        this.tasteRoomMemberService = tasteRoomMemberService;
    }

    @GetMapping("")
    @ApiOperation(value = "기본 전체목록")
    public ResponseEntity<ResResult> findAll(HttpServletRequest request, Pageable pageable, @RequestParam String restaurantId) throws Exception {
        ResResult resResult = new ResResult();
        List<TasteRoom> tasteRooms = service.selectTasteRoom(restaurantId);
        resResult.setData(tasteRooms);
        return ResponseEntity.ok(resResult);
    }

    @PostMapping()
    @ApiOperation(value = "등록")
    @Transactional
    public ResponseEntity<TasteRoom> regist(HttpServletRequest request, @RequestBody TasteRoomParam tasteRoomParam) throws Exception {
        TasteRoom tasteRoom = new TasteRoom();
        tasteRoom.setId(UUID.randomUUID().toString());
        tasteRoom.setContent(tasteRoomParam.getContent());
        Restaurant restaurant = this.restaurantService.findById(tasteRoomParam.getRestaurantId());
        tasteRoom.setRestaurant(restaurant);
        var user = this.userToken.findUserByDb();
        tasteRoom.setUser(user);
        tasteRoom.setCreateDate(new Date());
        tasteRoom.setPeopleNum(tasteRoomParam.getPeopleNum());
        tasteRoom.setMeetPaymentType(tasteRoomParam.getMeetPaymentType());
        TasteRoom result = this.service.regist(tasteRoom);
        TasteRoomMember tasteRoomMember = new TasteRoomMember();
        tasteRoomMember.setId(UUID.randomUUID().toString());
        tasteRoomMember.setTasteRoom(tasteRoom);
        tasteRoomMember.setUser(user);
        tasteRoomMember.setCreateDate(new Date());
        this.tasteRoomMemberService.regist(tasteRoomMember);
        return ResponseEntity.ok(result);
    }
}