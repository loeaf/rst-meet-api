package com.loeaf.siginin.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.loeaf.rstmeet.model.ReView;
import com.loeaf.rstmeet.model.TasteRoomMember;
import com.loeaf.siginin.types.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "Account")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    private String id;
    @Column
    private String loginId;
    @Column
    private String password;
    @Column
    private AccountType type;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "writer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReView> reViews;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TasteRoomMember> attendantTasteRooms;
}