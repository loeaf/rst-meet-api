package com.loeaf.siginin.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.loeaf.siginin.types.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "Account")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account {
    @Id
    private String id;
    @Column
    private String loginId;
    @Column
    private String password;
    @Column
    private AccountType type;
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private User user;
}