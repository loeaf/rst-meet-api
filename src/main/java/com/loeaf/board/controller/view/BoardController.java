package com.loeaf.board.controller.view;

import com.loeaf.board.domain.Board;
import com.loeaf.board.domain.BoardContent;
import com.loeaf.board.model.BoardForm;
import com.loeaf.board.domain.SampleCsv;
import com.loeaf.board.service.BoardContentService;
import com.loeaf.board.service.BoardService;
import com.loeaf.file.service.SampleDataParserService;
import com.loeaf.common.misc.PageSize;
import com.loeaf.common.misc.Paginator;
import com.loeaf.common.misc.PaginatorInfo;
import com.loeaf.file.domain.FileInfo;
import com.loeaf.file.service.FileInfoService;
import com.loeaf.siginin.model.User;
import com.loeaf.siginin.util.UserInfoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardContentService boardContentService;
    private final SampleDataParserService sampleDataParserService;
    private final FileInfoService fileInfoService;

    @GetMapping("/board")
    public String getNoticePage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            Model model) {

        PageSize ps = new PageSize();
        Page<Board> noticePage = boardService.findAllPgByStartPg(page -1, ps.getContent());
        model.addAttribute("boardPage", noticePage);

        PaginatorInfo pageNav = Paginator.getPaginatorMap(noticePage, ps);
        model.addAttribute("pageInfo", pageNav);

        return "board";
    }

    @GetMapping("/board/{id}")
    public String getNotice(@PathVariable(value = "id") Long id, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "content";
    }

    @PostMapping("/board")
    public String addNotice(@Valid @ModelAttribute BoardForm boardForm) {
        var vo = boardForm2Board(boardForm);
        this.boardService.regist(vo);
        return "redirect:/board";
    }

    @DeleteMapping("/board/{id}")
    public String deleteNotice(@PathVariable(value = "id") Long id) {
        boardService.delete(id);
        return "redirect:/board";
    }

    @PutMapping("/board")
    public String modifyNotice(@Valid @ModelAttribute BoardForm boardForm) {
        // ???
        Board board = new Board();
        board.setTitle(boardForm.getTitle());
        boardService.update(boardForm.getId(), board);
        return "redirect:/board";
    }

    @GetMapping("/edit")
    public String getEditForm() {
        return "edit";
    }

    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable(value = "id") Long id, Model model) {
        Board board = boardService.findById(id);
        model.addAttribute("board", board);
        return "edit";
    }

    /**
     * 게시판에의 Form 정보를 파일이 포함되지 않은 기본적인 Board 테이블에 매핑하기 위한 자료를 생성합니다.
     * @param boardForm
     * @return
     */
    private Board boardForm2Board(BoardForm boardForm) {
        BoardContent boardContent = boardContentService.regist(
                BoardContent.builder().content(boardForm.getContent()).build());
        User user = UserInfoUtil.getMyUserObj();
        var board = Board.builder()
                .title(boardForm.getTitle())
                .boardContent(boardContent)
                .user(user)
                .build();;
        return board;
    }

    /**
     * 파일에 있는 내부 데이터 파싱
     * @param fileInfos
     */
    @Transactional
    public void procAddCPFile(List<FileInfo> fileInfos) {
        List<SampleCsv> csvFileDatas = null;
        try {
            for (FileInfo fileInfo : fileInfos) {
                var cpFileInfoObj = registFile(fileInfo);
                registFileData(csvFileDatas, fileInfo);
                // service에서 samplecsv 데이터를 regist All regist 하는거 한방!
            }
        } catch (Exception e) {
            fileInfos.forEach(p -> new File(p.toString()).delete());
            e.printStackTrace();
        }
    }

    private FileInfo registFile(FileInfo fileInfo) {
        var fileInfoObj = this.fileInfoService.regist(fileInfo);
        return fileInfoObj;
    }

    /**
     *
     * @param csvFileDatas
     * @param fileInfo
     * @throws IOException
     */
    private void registFileData(List<SampleCsv> csvFileDatas, FileInfo fileInfo) throws IOException {
        csvFileDatas = this.sampleDataParserService.procParseFile(fileInfo.toString());
        csvFileDatas.forEach(p -> p.setFileInfo(fileInfo));
    }


}
