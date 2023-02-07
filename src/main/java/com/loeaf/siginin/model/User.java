package com.loeaf.siginin.model;

import com.loeaf.common.misc.BizField;
import com.loeaf.rstmeet.model.TasteRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity(name = "user")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column()
    @BizField(bizKey = true, order = 0)
    private String id;
    @Column()
    private String nickName;
    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TasteRoom> userId;

    @OneToMany(mappedBy = "joinId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TasteRoom> joinId;
}
