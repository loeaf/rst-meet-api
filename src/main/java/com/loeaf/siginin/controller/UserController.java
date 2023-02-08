package com.loeaf.siginin.controller;

import com.loeaf.siginin.dto.param.UserParam;
import com.loeaf.siginin.service.SigininService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final SigininService sigininService;

    @GetMapping
    public String getSignUpForm() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public ResponseEntity<Object> signUp(HttpServletRequest request,
                                         @RequestBody UserParam userForm) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        return ResponseEntity.ok(sigininService.save(userForm));
    }

}
