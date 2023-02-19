package com.loeaf.siginin.model;

import com.loeaf.rstmeet.model.Chatting;
import com.loeaf.rstmeet.model.ReView;
import com.loeaf.rstmeet.model.TasteRoom;
import com.loeaf.rstmeet.model.TasteRoomMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @Column()
//    @BizField(bizKey = true, order = 0)
    private String id;
    @Column()
    private String nickName;
//    @ManyToMany
//    @JoinTable(name = "user_role",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TasteRoom> userId;

    @OneToMany(mappedBy = "join", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TasteRoom> joinId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chatting> chattings;
    @OneToMany(mappedBy = "writer",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<ReView> reViews;

    @OneToMany(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private List<TasteRoomMember> attendantTasteRooms;
}
