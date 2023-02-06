package com.loeaf.rstmeet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "TasteRoom")
@AllArgsConstructor
@NoArgsConstructor
public class TasteRoom {
    @Id
    private String Id;
}