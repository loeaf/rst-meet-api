package com.loeaf.rstmeet.controller;

import com.loeaf.rstmeet.dto.ResResult;
import com.loeaf.rstmeet.dto.params.TasteRoomMemberParam;
import com.loeaf.rstmeet.model.TasteRoom;
import com.loeaf.rstmeet.model.TasteRoomMember;
import com.loeaf.rstmeet.service.TasteRoomMemberService;
import com.loeaf.rstmeet.service.TasteRoomService;
import com.loeaf.siginin.dto.UserToken;
import com.loeaf.siginin.model.User;
import com.loeaf.siginin.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/TasteRoomMember")
@Api(value = "TasteRoomMember")
public class TasteRoomMemberRestController {

    private TasteRoomMemberService service;
    private TasteRoomService tasteRoomService;
    private AccountService accountService;
    @Autowired
    private UserToken userToken;

    public TasteRoomMemberRestController(TasteRoomMemberService service, TasteRoomService tasteRoomService, AccountService accountService) {
        this.service = service;
        this.tasteRoomService = tasteRoomService;
        this.accountService = accountService;
    }

    @GetMapping("")
    @ApiOperation(value = "기본 전체목록")
    public ResponseEntity<Object> findAll(HttpServletRequest request, Pageable pageable) throws Exception {
        return null;
    }

    @PostMapping()
    @ApiOperation(value = "등록")
    public ResponseEntity<ResResult> regist(HttpServletRequest request, @RequestBody TasteRoomMemberParam tasteRoomMemberParam) throws Exception {
        TasteRoomMember tasteRoomMember = new TasteRoomMember();
        tasteRoomMember.setId(UUID.randomUUID().toString());
        TasteRoom tr = this.tasteRoomService.findById(tasteRoomMemberParam.getTasteRoomId());
        tasteRoomMember.setTasteRoom(tr);
//        Account ac = this.accountService.findById(tasteRoomMemberParam.getAccountId());
        User user = userToken.findUserByDb();
        tasteRoomMember.setUser(user);
        tasteRoomMember.setCreateDate(new Date());
        TasteRoomMember result = service.regist(tasteRoomMember);
        return ResponseEntity.ok(new ResResult(result));
    }
}