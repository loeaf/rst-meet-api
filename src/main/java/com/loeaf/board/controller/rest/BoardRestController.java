package com.loeaf.board.controller.rest;

import com.loeaf.board.domain.Board;
import com.loeaf.board.domain.BoardContent;
import com.loeaf.board.domain.BoardContentMapper;
import com.loeaf.board.service.BoardContentService;
import com.loeaf.board.service.BoardService;
import com.loeaf.common.domain.CmmnPageSize;
import com.loeaf.common.domain.CmmnPaginator;
import com.loeaf.common.domain.CmmnPaginatorInfo;
import com.loeaf.common.domain.PageParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;


/**
 *
 */
@Slf4j
@RestController
@RequestMapping("/boardRest")
@RequiredArgsConstructor
public class BoardRestController {
    private final BoardService boardService;
    private final BoardContentService boardContentService;

    /**
     *
     * @param nowPageNum
     * @return
     */
    @GetMapping()
    public PageParam<Board> getBoardInfo(
            @RequestParam(value = "lsDiffPage", defaultValue = "1") Integer nowPageNum) {
        Page<Board> boardInfoPage = boardService.findAllPgByStartPg(nowPageNum -1,CmmnPageSize.NOTICE.getContent());
        CmmnPaginatorInfo boardPageNav = CmmnPaginator.getPaginatorMap(boardInfoPage, CmmnPageSize.NOTICE);

        var sendParam = new PageParam<Board>();
        sendParam.setPage(boardInfoPage.getContent());
        sendParam.setPagenationInfo(boardPageNav);
        sendParam.setSize(boardInfoPage.getSize());
        sendParam.setNowPageNum(nowPageNum -1);;
        return sendParam;
    }

    /**
     *
     * @param nowPageNum
     * @return
     */
    @GetMapping("/contents/")
    public PageParam<List<BoardContentMapper>> getBoardInfoByContents(
            @RequestParam(value = "lsDiffPage", defaultValue = "1") Integer nowPageNum,
            @RequestParam(value = "contents", required = true) String contents) {
        Page<List<BoardContentMapper>> boardInfoPage = boardContentService.findByContentLikeAndStartPg(nowPageNum-1, CmmnPageSize.NOTICE.getContent(), contents);
        CmmnPaginatorInfo boardPageNav = CmmnPaginator.getPaginatorMap(boardInfoPage, CmmnPageSize.NOTICE);

        var sendParam = new PageParam<List<BoardContentMapper>>();
        sendParam.setPage(boardInfoPage.getContent());
        sendParam.setPagenationInfo(boardPageNav);
        sendParam.setSize(boardInfoPage.getSize());
        sendParam.setNowPageNum(nowPageNum -1);;
        return sendParam;
    }
}
