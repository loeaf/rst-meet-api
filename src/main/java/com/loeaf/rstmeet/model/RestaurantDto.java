package com.loeaf.rstmeet.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
    private String id;
    // 맛집번호
    private Integer restaurantNumber;
    // 맛집명
    private String name;
    // 한글도로명주소
    private String roadAddress;
    // 한글지번주소
    private String jibunAddress;
    // 영어주소
    private String englishAddress;
    // 요약주소
    private String miniAddress;
    // 위도
    private Double latitude;
    // 경도
    private Double longitude;
    // 거리
    private Double distance;
    // 지리정보
    private Point  geoInfo;
    // 등록일
    private Date regDate;
    // 전화번호
    private String phoneNumber;
    // 휴무일
    private String holiday;
    // 대표메뉴
    private String representativeMenu;
    // 이미지경로
    private String imagePath;
    // 이미지명
    private String imageName;
}