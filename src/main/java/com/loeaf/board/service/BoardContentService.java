package com.loeaf.board.service;

import com.loeaf.board.domain.BoardContent;
import com.loeaf.board.domain.BoardContentMapper;
import com.loeaf.common.misc.Service;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;

public interface BoardContentService extends Service<BoardContent, Long> {
    Page<List<BoardContentMapper>> findByContentLikeAndStartPg(Integer startPage, Integer contentsSize, String boardContents);
}
