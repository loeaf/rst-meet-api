package com.loeaf.rstmeet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "Chatting")
@AllArgsConstructor
@NoArgsConstructor
public class Chatting {
    @Id
    private String id;

    @Column
    private String content;

    @Column
    private Date createDate;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private TasteRoom tasteRoom;

}