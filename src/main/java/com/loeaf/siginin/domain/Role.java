package com.loeaf.siginin.domain;

import com.loeaf.common.domain.Domain;
import com.loeaf.common.misc.BizField;
import com.loeaf.siginin.types.Authority;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "role")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role extends Domain {
    @Column(length = 10, nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    @BizField
    private Authority authority;
}
