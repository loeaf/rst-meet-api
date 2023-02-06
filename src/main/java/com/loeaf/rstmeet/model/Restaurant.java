package com.loeaf.rstmeet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "Restaurant")
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    private String Id;
}