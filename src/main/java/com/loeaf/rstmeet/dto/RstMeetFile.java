package com.loeaf.rstmeet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RstMeetFile {
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
}