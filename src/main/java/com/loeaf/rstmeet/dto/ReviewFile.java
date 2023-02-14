package com.loeaf.rstmeet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewFile {
    // 식당번호
    private Integer restaurantId;
    // 내용
    private String content;
    // 대표리뷰여부
    private String isMain;
}