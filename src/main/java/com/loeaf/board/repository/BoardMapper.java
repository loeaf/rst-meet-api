package com.loeaf.board.repository;

import com.loeaf.board.domain.Board;
import com.loeaf.common.conn.BoardConnMapper;
import com.loeaf.common.misc.Mapper;

@BoardConnMapper
public interface BoardMapper extends Mapper<Board, Long> {
    Integer getTotcnt();
}
