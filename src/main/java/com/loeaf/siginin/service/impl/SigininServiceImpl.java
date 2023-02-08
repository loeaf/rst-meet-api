package com.loeaf.siginin.service.impl;

import com.loeaf.siginin.dto.param.UserParam;
import com.loeaf.siginin.exception.DuplicateDataException;
import com.loeaf.siginin.model.Account;
import com.loeaf.siginin.model.User;
import com.loeaf.siginin.service.AccountService;
import com.loeaf.siginin.service.RoleService;
import com.loeaf.siginin.service.SigininService;
import com.loeaf.siginin.service.UserService;
import com.loeaf.siginin.types.AccountType;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SigininServiceImpl implements SigininService {
    @Autowired
    final UserService userService;
    @Autowired
    final AccountService accountService;
    @Autowired
    final RoleService roleService;
    final PasswordEncoder passwordEncoder;


    @Transactional
    public User save(UserParam userParam) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        checkDuplication(userParam);
        User user = new User();
//        Set<Role> roles = new HashSet<>();
//        roles.add(roleService.findByBizKey(Role.builder().authority(Authority.USER).build()));
//        user.setRoles(roles);
        user.setId(UUID.randomUUID().toString());
        user.setNickName(userParam.getNickName());
        var userObj = userService.regist(user);
        Account account = new Account();
        account.setType(AccountType.valueOf(userParam.getAccountType()));
        account.setId(UUID.randomUUID().toString());
        account.setPassword(passwordEncoder.encode(userParam.getPassword()));
        account.setUser(userObj);
        var accountObj = accountService.regist(account);
        return userObj;
    }

    @SneakyThrows
    private void checkDuplication(UserParam userParam) {
        AccountType accountType = AccountType.valueOf(userParam.getAccountType());
        Account existsEmail = accountService.findByLoginIdAndType(userParam.getLoginId(), accountType);
        if (existsEmail != null) {
            throw new DuplicateDataException(existsEmail.toString());
        }
        User user = userService.findByNickName(userParam.getNickName());
        if(user != null) {
            var existsNick = userService.findByNickName(user.getNickName());
            if (existsNick != null) {
                throw new DuplicateDataException(user.toString());
            }
        }
    }
}
