package com.loeaf.rstmeet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RstMeetFile {
    private Integer rstMeetFileNumber;
    // 카테고리
    private String category;
    // 지역
    private String area;
    // 식당
    private String restaurant;
    // 대표메뉴
    private String representativeMenu;
    // 휴무일
    private String holiday;
    // 전화번호
    private String phoneNumber;
    // 한국도로명 주소
    private String koreanRoadAddress;
    // 한국지번 주소
    private String koreanJibunAddress;
    // 요약주소 주소
    private String specAddr;
    // 영문주소
    private String englishAddress;
    // 위도
    private String lat;
    // 경도
    private String log;

}