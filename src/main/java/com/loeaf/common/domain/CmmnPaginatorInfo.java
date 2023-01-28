package com.loeaf.common.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CmmnPaginatorInfo {
    private Integer startPageNum;
    private Integer lastPageNum;
    private Integer previousPaging;
    private Integer nextPaging;
}