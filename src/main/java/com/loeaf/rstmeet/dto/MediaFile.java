package com.loeaf.rstmeet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaFile {
    // 미디어번호
    private Integer mediaId;
    // 이름
    private String name;
    // 이미지경로
    private String path;
    // 레스토랑UUID
    private String restaurantUuid;
}