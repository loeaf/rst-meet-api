package com.loeaf.board.service.impl;

import com.loeaf.board.domain.BoardContent;
import com.loeaf.board.domain.BoardContentMapper;
import com.loeaf.board.repository.BoardContentRepository;
import com.loeaf.board.service.BoardContentService;
import com.loeaf.common.misc.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardContentServiceImpl
        extends ServiceImpl<BoardContentRepository, BoardContent, Long>
        implements BoardContentService {
    private final BoardContentRepository jpaRepo;

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new BoardContent());
    }

    @Override
    public Page<List<BoardContentMapper>> findByContentLikeAndStartPg(Integer startPage, Integer contentsSize,
                                                                            String boardContents) {
        PageRequest pageRequest =
                PageRequest.of(startPage,
                        contentsSize, Sort.Direction.DESC, "id");
        var result = jpaRepo.getContentContains("%"+boardContents+"%", pageRequest);;
        return result;
    }
}
