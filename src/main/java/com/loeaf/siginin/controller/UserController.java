package com.loeaf.siginin.controller;

import com.loeaf.siginin.domain.User;
import com.loeaf.siginin.model.UserForm;
import com.loeaf.siginin.service.SigininService;
import com.loeaf.siginin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.input.ObservableInputStream;
import org.hibernate.EntityMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Observable;

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

    @PostMapping
    public String signUp(@Valid @ModelAttribute UserForm userForm) {

        sigininService.save(convertFormToUser(userForm));
        return "redirect:/login";
    }

    private User convertFormToUser(UserForm userForm) {
        return User.builder()
                .email(userForm.getEmail())
                .nickName(userForm.getNickName())
                .password(userForm.getPassword())
                .build();
    }
}
