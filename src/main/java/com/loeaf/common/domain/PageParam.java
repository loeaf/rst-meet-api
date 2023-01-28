package com.loeaf.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * LHDT 페이지네이션 지원을 위한 페이징 공통 인터페이스
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageParam<T> {
    private List<T> page;
    private CmmnPaginatorInfo pagenationInfo;
    private Integer size;
    private Integer nowPageNum;
}
