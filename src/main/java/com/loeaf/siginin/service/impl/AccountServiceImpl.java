package com.loeaf.siginin.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.siginin.model.Account;
import com.loeaf.siginin.repository.AccountRepository;
import com.loeaf.siginin.service.AccountService;
import com.loeaf.siginin.types.AccountType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl
        extends ServiceImpl<AccountRepository, Account, String>
        implements AccountService {
    private final AccountRepository jpaRepo;

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new Account());
    }

    @Override
    public Account findByLoginIdAndType(String loginId, AccountType type) {
        return jpaRepo.findByLoginIdAndType(loginId, type);
    }
}
