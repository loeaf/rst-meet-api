package com.loeaf.board.domain;

import com.loeaf.common.domain.Domain;
import com.loeaf.common.misc.BizField;
import com.loeaf.siginin.domain.User;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "board")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends Domain {

    @Column(length = 100, nullable = false)
    @BizField
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_content_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private BoardContent boardContent;
}
