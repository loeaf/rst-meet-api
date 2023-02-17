package com.loeaf.siginin.util;

import com.loeaf.siginin.model.User;
import com.loeaf.siginin.security.CustomUserInfo;
import com.loeaf.siginin.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Data
@Component
public class UserInfoUtil {
    @Autowired
    private UserService beanUser;
    static UserService userService;

    @PostConstruct
    private void initialize() {
        userService = beanUser;
    }

    /**
     * 현 유저 권한에 대한 정보를 가져온다.
     * @return
     */
    public static CustomUserInfo get() {
        CustomUserInfo userDetails = (CustomUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails;
    }
    /**
     * 현 유저 권한에 대한 정보를 가져온다.
     * @return
     */
    public static User getMyUserObj() {
        CustomUserInfo userDetails = (CustomUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        var p = userService.findById(userDetails.getId());
        return p;
    }

}
