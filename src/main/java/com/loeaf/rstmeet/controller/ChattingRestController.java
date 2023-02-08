package com.loeaf.rstmeet.controller;

import com.loeaf.rstmeet.dto.params.ChatParam;
import com.loeaf.rstmeet.model.Chatting;
import com.loeaf.rstmeet.model.TasteRoom;
import com.loeaf.rstmeet.service.ChattingService;
import com.loeaf.rstmeet.service.TasteRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/Chatting")
@Api(value = "Chatting")
public class ChattingRestController {

    private ChattingService service;
    private TasteRoomService tasteRoomService;

    public ChattingRestController(ChattingService service, TasteRoomService tasteRoomService) {
        this.service = service;
        this.tasteRoomService = tasteRoomService;
    }

    @GetMapping("")
    @ApiOperation(value = "기본 전체목록")
    public ResponseEntity<Object> findAll(HttpServletRequest request, Pageable pageable, @RequestParam String roomId) throws Exception {
        return ResponseEntity.ok(service.findById(roomId));
    }

    @PostMapping()
    @ApiOperation(value = "등록")
    public ResponseEntity<Object> regist(HttpServletRequest request,
                                         @RequestBody ChatParam chatParam) throws Exception {
        Chatting chatting = new Chatting();
        chatting.setContent(chatParam.getContent());
        TasteRoom tasteRoom = tasteRoomService.findById(chatParam.getTasteRoomId());
        chatting.setTasteRoom(tasteRoom);
        chatting.setId(chatParam.getUserId());
        chatting.setCreateDate(new Date());
        return ResponseEntity.ok(service.regist(chatting));
    }
}