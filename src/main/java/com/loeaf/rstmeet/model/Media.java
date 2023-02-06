package com.loeaf.rstmeet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "Media")
@AllArgsConstructor
@NoArgsConstructor
public class Media {
    @Id
    private String id;
    // 파일명
    @Column
    private String name;
    // 경로
    @Column
    private String path;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private Restaurant restaurant;
}