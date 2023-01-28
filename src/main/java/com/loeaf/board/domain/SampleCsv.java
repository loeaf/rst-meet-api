package com.loeaf.board.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.loeaf.common.domain.Domain;
import com.loeaf.file.domain.FileInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class SampleCsv extends Domain {
    private String col0;
    private String col1;

    /**
     * COMMENT '파일 id'"
     */
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="file_id")
    private FileInfo fileInfo;
}
