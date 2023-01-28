package com.loeaf.siginin.service.impl;

import com.loeaf.siginin.domain.Role;
import com.loeaf.siginin.domain.User;
import com.loeaf.siginin.exception.DuplicateDataException;
import com.loeaf.siginin.repository.UserRepository;
import com.loeaf.siginin.service.RoleService;
import com.loeaf.siginin.service.SigininService;
import com.loeaf.siginin.service.UserService;
import com.loeaf.siginin.types.Authority;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SigininServiceImpl implements SigininService {
    @Autowired
    final UserService userService;
    @Autowired
    final RoleService roleService;
    final PasswordEncoder passwordEncoder;


    public User save(User user) {
        checkDuplication(user);
        encodePassword(user);
        setRoles(user);
        return userService.regist(user);
    }

    @SneakyThrows
    private void checkDuplication(User user) {
        var existsEmail = userService.findByBizKey(User.builder().email(user.getEmail()).build());
        if (existsEmail != null) {
            throw new DuplicateDataException(user.getEmail());
        }
        var existsNick = userService.findByNickName(user.getNickName());
        if (existsNick != null) {
            throw new DuplicateDataException(user.getEmail());
        }
    }

    private void encodePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    private void setRoles(User user) {
        Set<Role> roles = new HashSet<>();
        try {
            roles.add(roleService.findByBizKey(Role.builder().authority(Authority.USER).build()));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        user.setRoles(roles);
    }
}
