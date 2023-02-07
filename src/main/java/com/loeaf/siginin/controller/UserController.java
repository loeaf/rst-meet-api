package com.loeaf.siginin.controller;

import com.loeaf.siginin.service.SigininService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final SigininService sigininService;

    @GetMapping
    public String getSignUpForm() {
        return "signUp";
    }
//
//    @PostMapping
//    public String signUp(@Valid @ModelAttribute UserParam userForm) {
//
//        sigininService.save());
//        return "redirect:/login";
//    }

}
