package com.loeaf.board.service.impl;

import com.loeaf.board.domain.Board;
import com.loeaf.board.repository.BoardRepository;
import com.loeaf.board.service.BoardService;
import com.loeaf.common.misc.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl
        extends ServiceImpl<BoardRepository, Board, Long>
        implements BoardService {
    private final BoardRepository jpaRepo;

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new Board());
    }
}
