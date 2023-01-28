package com.loeaf.common.domain;


import org.springframework.data.domain.Page;

public final class CmmnPaginator {
    public static CmmnPaginatorInfo getPaginatorMap(Page<?> page, CmmnPageSize DSPageSize) {
        Integer pageNavCount = DSPageSize.getPageNavCount();
        Integer pageNumber = page.getNumber();

        Integer startPageNum = calcStartPageNum(pageNavCount, pageNumber);
        Integer lastPageNum = calcLastPageNum(page, pageNavCount, startPageNum);
        CmmnPaginatorInfo pageNav = new CmmnPaginatorInfo();
        pageNav.setStartPageNum(startPageNum);
        pageNav.setLastPageNum(lastPageNum);
        pageNav.setPreviousPaging(calcPreviousPaging(startPageNum));
        pageNav.setNextPaging(calcNextPaging(page, lastPageNum));
        return pageNav;
    }

    private static Integer calcNextPaging(Page<?> page, Integer lastPageNum) {
        return (lastPageNum + 1) <= (page.getTotalPages() - 1) ? (lastPageNum + 1) : null;
    }

    private static Integer calcPreviousPaging(Integer startPageNum) {
        return (startPageNum - 1) >= 0 ? (startPageNum - 1) : null;
    }

    private static int calcLastPageNum(Page<?> page, Integer pageNavCount, Integer startPageNum) {
        return Math.min(startPageNum + pageNavCount - 1, page.getTotalPages() - 1);
    }

    private static int calcStartPageNum(Integer pageNavCount, Integer pageNumber) {
        return (pageNumber / pageNavCount) * pageNavCount;
    }
}