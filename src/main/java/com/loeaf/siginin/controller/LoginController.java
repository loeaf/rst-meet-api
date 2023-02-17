package com.loeaf.siginin.controller;

import com.loeaf.siginin.dto.param.UserParam;
import com.loeaf.siginin.model.Account;
import com.loeaf.siginin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AccountService accountService;

    // post login
    @PostMapping("")
    public ResponseEntity<Object> login(HttpServletRequest request,
                                         @RequestBody UserParam userForm) {
        String jwt = accountService.login(userForm);
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("checkJwt")
    public ResponseEntity<Object> checkJwt(HttpServletRequest request,
                                           @RequestParam String jwt) {
        Account account = accountService.checkJwt(jwt);
        return ResponseEntity.ok(account);
    }
}
