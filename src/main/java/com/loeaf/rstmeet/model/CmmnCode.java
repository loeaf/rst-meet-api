package com.loeaf.rstmeet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "CmmnCode")
@AllArgsConstructor
@NoArgsConstructor
public class CmmnCode {
    @Id
    private String id;
    @Column
    private String codeName;
    // one to many parentCode Column
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private CmmnCode parentCode;

    @OneToMany(mappedBy = "parentCode", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CmmnCode> cmmnCodeList;

    @OneToMany(mappedBy = "countryType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Restaurant> restaurantsList;

    @OneToMany(mappedBy = "cityType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Restaurant> cityList;
}