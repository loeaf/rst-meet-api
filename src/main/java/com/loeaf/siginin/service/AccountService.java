package com.loeaf.siginin.service;

import com.loeaf.common.misc.Service;
import com.loeaf.siginin.dto.param.UserParam;
import com.loeaf.siginin.model.Account;
import com.loeaf.siginin.types.AccountType;

public interface AccountService extends Service<Account, String> {
    Account findByLoginIdAndType(String loginId, AccountType type);

    String login(UserParam userForm);

    Account checkJwt(String jwt);
}
