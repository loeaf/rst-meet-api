package com.loeaf.file.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.loeaf.board.domain.SampleCsv;
import com.loeaf.common.domain.Domain;
import com.loeaf.common.misc.Action;
import com.loeaf.file.domain.listener.FileInfoListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 파일에 대한 기본 정보를 구현하는 Model
 */
@Entity
@EntityListeners(FileInfoListener.class)
@Table(name="file_info")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo extends Domain implements Action {
    /**
     * 파일명
     */
    @NotNull
    @Column(name = "file_name")
    private String fileName;

    /**
     * 파일경로
     */
    @NotNull
    @Column(name = "file_path")
    private String filePath;

    /**
     * 파일경로
     */
    @NotNull
    @Column(name = "origin_file_name")
    private String originFileName;

    /**
     * 확장자
     */
    @NotNull
    @Column(name = "file_ext")
    private String fileExtention;

    @Override
    public String toString() {
        return this.getFilePath() + "/" + this.getFileName() + "." + this.getFileExtention();
    }

    @Override
    public void delete() {
        new File(this.toString()).delete();
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "fileInfo" +
            "", fetch=FetchType.LAZY, cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<SampleCsv> sampleCsvDetail = new ArrayList<>();

    public void addCityInfo(SampleCsv CPReportDetail) {
        if(CPReportDetail.getFileInfo() != this)
            CPReportDetail.setFileInfo(this);
        this.sampleCsvDetail.add(CPReportDetail);
    }
    public void addCityInfos(List<SampleCsv> CPReportDetail) {
        CPReportDetail.forEach(p -> {
            if(p.getFileInfo() != this)
                p.setFileInfo(this);
            this.sampleCsvDetail.add(p);
        });
    }
}
