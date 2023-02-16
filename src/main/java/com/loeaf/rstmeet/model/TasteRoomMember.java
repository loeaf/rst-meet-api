package com.loeaf.rstmeet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.loeaf.siginin.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "TasteRoomMember")
@AllArgsConstructor
@NoArgsConstructor
public class TasteRoomMember {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private TasteRoom tasteRoom;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Account account;

    @Column
    private Date createDate;
}