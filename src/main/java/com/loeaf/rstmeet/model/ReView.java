package com.loeaf.rstmeet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.loeaf.siginin.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "ReView")
@AllArgsConstructor
@NoArgsConstructor
public class ReView {
    @Id
    private String id;

    // 내용
    @Column
    private String content;

    // 등록일
    @Column
    private Date regDate;

    // 대표리뷰여부
    @Column
    private String isMain;

    // 작성자
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Account writer;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Restaurant restaurant;

}