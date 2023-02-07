package com.loeaf.siginin.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.siginin.model.User;
import com.loeaf.siginin.repository.UserRepository;
import com.loeaf.siginin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class UserServiceImpl
        extends ServiceImpl<UserRepository, User, String>
        implements UserService {
    private final UserRepository userRepository;

    @PostConstruct
    private void init() {
        super.set(userRepository, new User());
    }
    @Override
    public User findByNickName(String nickName) {
        return userRepository.findByNickName(nickName);
    }
}
