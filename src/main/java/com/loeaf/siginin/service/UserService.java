package com.loeaf.siginin.service;

import com.loeaf.common.misc.Service;
import com.loeaf.siginin.model.User;

public interface UserService extends Service<User, String> {
    public User findByNickName(String nickName);
}
