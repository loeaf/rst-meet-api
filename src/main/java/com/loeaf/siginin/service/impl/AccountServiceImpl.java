package com.loeaf.siginin.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.siginin.dto.param.UserParam;
import com.loeaf.siginin.model.Account;
import com.loeaf.siginin.repository.AccountRepository;
import com.loeaf.siginin.service.AccountService;
import com.loeaf.siginin.types.AccountType;
import com.loeaf.siginin.util.JwtManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl
        extends ServiceImpl<AccountRepository, Account, String>
        implements AccountService {
    private final AccountRepository jpaRepo;
    final PasswordEncoder passwordEncoder;
    @Autowired
    JwtManager jwtManager;

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new Account());
    }

    @Override
    public Account findByLoginIdAndType(String loginId, AccountType type) {
        return jpaRepo.findByLoginIdAndType(loginId, type);
    }

    @Override
    public String login(UserParam userForm) {
        // 아이디와 비밀번호 확인
        if(userForm.getLoginId() == null || userForm.getPassword() == null) {
            return null;
        }
        // kakao login
        if(userForm.getAccountType().equals(AccountType.KAKAO.getValue())) {
            Account account = this.jpaRepo.findByLoginIdAndType(userForm.getLoginId(), AccountType.KAKAO);
            return jwtManager.generateJwtToken(account);
        } else if(userForm.getAccountType().equals(AccountType.EMAIL.getValue())) {
            Account account = this.jpaRepo.findByLoginIdAndType(userForm.getLoginId(), AccountType.EMAIL);
            if(passwordEncoder.matches(userForm.getPassword(), account.getPassword())) {
                return jwtManager.generateJwtToken(account);
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    @Override
    public Account checkJwt(String jwt) {
        var result = jwtManager.getUsernameFromToken(jwt);
        return result;
    }
}
