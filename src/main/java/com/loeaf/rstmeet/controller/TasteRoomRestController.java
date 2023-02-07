package com.loeaf.rstmeet.controller;

import com.loeaf.rstmeet.model.TasteRoom;
import com.loeaf.rstmeet.service.TasteRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/TasteRoom")
@Api(value = "TasteRoom")
public class TasteRoomRestController {

    private TasteRoomService service;

    public TasteRoomRestController(TasteRoomService service) {
        this.service = service;
    }

    @GetMapping("")
    @ApiOperation(value = "기본 전체목록")
    public ResponseEntity<List<TasteRoom>> findAll(HttpServletRequest request, Pageable pageable) throws Exception {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping()
    @ApiOperation(value = "등록")
    public ResponseEntity<TasteRoom> regist(HttpServletRequest request, @RequestBody TasteRoom tasteRoom) throws Exception {
        return ResponseEntity.ok(this.service.regist(tasteRoom));
    }
}