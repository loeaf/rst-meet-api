### 공통 파일 사용 예시
- 파일 처리를 위한 공통 기능을 설명한다.
- MultipartFileMaster를 상속 받는다. MultipartFileMaster에는 MultipartFile 처리를 위한 기능이 있다.

통신을 위한 모델 정의
```java
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardForm extends MultipartFileMaster {
    public BoardForm(List<MultipartFile> files) {
        super(files);
    }
}
```

단순 Multipart Data 받기위한 Controller 기능 구현 방법
```java
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/board")
    public String addNotice(@Valid @ModelAttribute BoardForm boardForm) {
        var vo = boardForm2Board(boardForm);
        this.boardService.regist(vo);
        return "redirect:/board";
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
}
```

파일 처리 실패 시 삭제 기능을 보장하기 위한 기능 구현 방법
```java
class Example {
    /**
     * 파일에 있는 내부 데이터 파싱
     * @param fileInfos
     */
    @Transactional
    public void procAddCPFile(List<FileInfo> fileInfos) {
        List<SampleCsv> csvFileDatas = null;
        try {
            for (FileInfo fileInfo : fileInfos) {
                var cpFileInfoObj = registFile(fileInfo); // File을 JPA를 통해 등록
                registFileData(csvFileDatas, fileInfo);
                // JPA Repository를 통해 regist All 하는거 한방!
            }
        } catch (Exception e) {
            fileInfos.forEach(p -> new File(p.toString()).delete());
            e.printStackTrace();
        }
    }
    
    private void registFileData(List<SampleCsv> csvFileDatas, FileInfo fileInfo) throws IOException {
        csvFileDatas = this.sampleDataParserService.procParseFile(fileInfo.toString());
        csvFileDatas.forEach(p -> p.setFileInfo(fileInfo));
    }
}

```
