package com.loeaf.rstmeet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuFile {
    // 식당번호
    private Integer restaurantId;
    // 메뉴이름
    private String name;
    // 메뉴종류
    private String menuType;
    // 메뉴량
    private String menuAmount;
    // 메뉴가격
    private String price;
    // 메뉴사진
    private String photoUrl;
    // 메뉴설명
    private String description;
    // 대표메뉴여부
    private String isMain;
}