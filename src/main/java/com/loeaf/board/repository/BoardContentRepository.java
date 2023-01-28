package com.loeaf.board.repository;

import com.loeaf.board.domain.BoardContent;
import com.loeaf.board.domain.BoardContentMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardContentRepository extends JpaRepository<BoardContent, Long> {
    @Query(value = "SELECT BOARD.ID as id, BOARD.TITLE as title, BOARD_CONTENT.CONTENT as content " +
            "FROM BOARD, BOARD_CONTENT " +
            "WHERE BOARD_CONTENT.ID = BOARD.ID " +
            "AND BOARD_CONTENT.CONTENT LIKE ?1",
            nativeQuery = true)
    Page<List<BoardContentMapper>> getContentContains(String boardContent, Pageable pageable);
}
