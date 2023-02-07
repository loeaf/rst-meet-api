package com.loeaf.rstmeet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.loeaf.siginin.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private Restaurant restaurantId;
    // 생성계정
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private User userId;

    // 참여계정
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private User joinId;

    // 챗팅방내용
    private String content;

    // 챗팅방생성일
    private String createDate;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chatting> chattings;
}