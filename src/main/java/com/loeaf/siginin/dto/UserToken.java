package com.loeaf.siginin.dto;

import com.loeaf.siginin.model.User;
import com.loeaf.siginin.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserToken {
    private User user;
    private String token;

    @Autowired
    private UserService userService;

    public User findUserByDb() {
        return this.userService.findById(this.user.getId());
    }
}