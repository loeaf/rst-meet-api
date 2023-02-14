package com.loeaf.rstmeet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "Menu")
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    @Id
    private String id;

    // 메뉴이름
    @Column
    private String name;

    // 메뉴가격
    @Column
    private String price;

    // 메뉴사진
    @Column
    private String photoUrl;

    // 메뉴설명
    @Column
    private String description;

    // 메뉴종류
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private CmmnCode menuType;

    // 등록일
    @Column
    private Date regDate;

    // 대표메뉴여부
    @Column
    private String isMain;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Restaurant restaurant;

}