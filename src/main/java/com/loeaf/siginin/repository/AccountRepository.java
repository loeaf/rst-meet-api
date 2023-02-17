package com.loeaf.siginin.repository;

import com.loeaf.siginin.model.Account;
import com.loeaf.siginin.types.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByLoginIdAndPassword(String loginId, String password);
    Account findByLoginIdAndType(String loginId, AccountType type);
}