package com.loeaf.board.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.loeaf.common.domain.Domain;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="board_content")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardContent extends Domain {
    @Column(length = 2000, nullable = false)
    private String content;
}
