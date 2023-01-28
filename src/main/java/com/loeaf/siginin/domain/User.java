package com.loeaf.siginin.domain;

import com.loeaf.common.domain.Domain;
import com.loeaf.common.misc.BizField;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends Domain {
    @Column(length = 50, nullable = false, unique = true)
    @BizField(bizKey = true, order = 0)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 12, nullable = false, unique = true)
    private String nickName;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
