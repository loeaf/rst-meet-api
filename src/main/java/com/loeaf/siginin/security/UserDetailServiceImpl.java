package com.loeaf.siginin.security;

import com.loeaf.siginin.domain.Role;
import com.loeaf.siginin.domain.User;
import com.loeaf.siginin.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        var userDomain = User.builder().email(email).build();
        //보강
        User user = null;
        try {
            user = userService.findByBizKey(userDomain);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (user == null) {
            throw new UsernameNotFoundException(email + " is not found");
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getAuthority().name()));
        }

        CustomUserInfo userDetails = new CustomUserInfo(email, user.getPassword(), authorities);
        userDetails.setNickName(user.getNickName());
        userDetails.setId(user.getId());
        return userDetails;
    }
}
