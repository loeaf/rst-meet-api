package com.loeaf.rstmeet.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.rstmeet.model.UserAccount;
import com.loeaf.rstmeet.repository.UserAccountRepository;
import com.loeaf.rstmeet.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl
        extends ServiceImpl<UserAccountRepository, UserAccount, String>
        implements UserAccountService {
    private final UserAccountRepository jpaRepo;

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new UserAccount());
    }
}
