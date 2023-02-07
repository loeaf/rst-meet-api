package com.loeaf.siginin.security;

import com.loeaf.siginin.model.Account;
import com.loeaf.siginin.model.Role;
import com.loeaf.siginin.repository.AccountRepository;
import com.loeaf.siginin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Component
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Autowired
    private AccountRepository accountRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) {
        var userDomain = Account.builder().loginId(email).build();
        //보강
        Account user = null;
        user = accountRepository.findByLoginId(userDomain);
        if (user == null) {
            throw new UsernameNotFoundException(email + " is not found");
        }

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : user.getUser().getRoles()){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getAuthority().name()));
        }

        CustomUserInfo userDetails = new CustomUserInfo(email, user.getPassword(), authorities);
        userDetails.setNickName(user.getUser().getNickName());
        userDetails.setId(user.getUser().getId());
        return userDetails;
    }
}
