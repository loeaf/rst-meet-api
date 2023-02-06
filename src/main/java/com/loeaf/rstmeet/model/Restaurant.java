package com.loeaf.rstmeet.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "Restaurant")
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    @Id
    private String id;
    // 맛집명
    @Column
    private String name;
    // 한글도로명주소
    @Column
    private String roadAddress;
    // 한글지번주소
    @Column
    private String jibunAddress;
    // 영어주소
    @Column
    private Long englishAddress;
    // 위도
    @Column
    private Long latitude;
    // 경도
    @Column
    private Long longitude;
    // 지리정보
    @Column
    private Point  geoInfo;
    // 등록일
    @Column
    private Date regDate;
    // 전화번호
    @Column
    private String phoneNumber;
    // 휴무일
    @Column
    private String holiday;
    // 대표메뉴
    @Column
    private String representativeMenu;
    // 음식종류
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private CmmnCode foodType;
    // 국가구분코드
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private CmmnCode countryType;
    // 도시구분코드
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @JsonBackReference
    private CmmnCode cityType;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Media> mediaList;
}