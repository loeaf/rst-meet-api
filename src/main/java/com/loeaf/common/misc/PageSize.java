package com.loeaf.common.misc;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class PageSize {
    private Integer content; //한페이지에 출력할 글의 갯수
    private Integer pageNavCount; //페이징 네비게이션에서 출력할 페이지의 갯수
    public PageSize() {
        this.content = 8;
        this.pageNavCount = 5;
    }
}
