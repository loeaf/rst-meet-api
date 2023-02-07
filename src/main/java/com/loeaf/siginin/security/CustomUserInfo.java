package com.loeaf.siginin.security;

import com.loeaf.siginin.types.Authority;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class CustomUserInfo extends User {
    private String id;
    private String nickName;

    public CustomUserInfo(String email, String password, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, authorities);
    }

    public String getEmail() {
        return super.getUsername();
    }

    public boolean hasAuthority(Authority authority) {
        Collection<GrantedAuthority> authorities = super.getAuthorities();

        return authorities.contains(new SimpleGrantedAuthority("ROLE_" + authority.name()));
    }
}
