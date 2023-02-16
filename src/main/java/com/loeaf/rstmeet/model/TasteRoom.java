package com.loeaf.rstmeet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.loeaf.siginin.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "TasteRoom")
@AllArgsConstructor
@NoArgsConstructor
public class TasteRoom {
    @Id
    private String id;
    // 맛집아이디
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Restaurant restaurant;
    // 생성계정
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private User user;

    // 참여계정
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private User join;

    // 챗팅방내용
    private String content;

    // 참여인원
    private Integer peopleNum;

    // 지불방법
    private String meetPaymentType;

    // 챗팅방생성일
    private Date createDate;

    @OneToMany(mappedBy = "tasteRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chatting> chattings = new ArrayList<>();

    @OneToMany(mappedBy = "tasteRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TasteRoomMember> attendantMembers = new ArrayList<>();
}