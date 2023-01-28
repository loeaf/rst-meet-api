### 공통 CRUD 사용 예시
- 객체 설계시 RDB에서 유일성 보장을 위한 본질 식별자를 Bizkey Annotation을 통해 지정해 준다
```java
import com.loeaf.common.domain.Domain;

@Entity
@Table(name = "board")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends Domain {

    @Column(length = 100, nullable = false)
    @BizField
    private String title;
}
```

- 인터페이스에서 Service를 상속받는다.
- 상속받은 서비스는 Service<도메인을 상속받은 Class, Long>으로 구성된다
```java
import com.loeaf.common.misc.Service;

public interface BoardService extends Service<Board, Long> {
}
```

- serviceImpl에서 serviceImpl을 상속받고 해당 Service를 구현 후 추상 Service에 객체 주입
```java
import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.board.repository.BoardRepository;

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
```

- 주입된 객체는 컨트롤러에서 아래와 같은 방식으로 활용가능
- 아래 예시는 게시판의 페이징 기능을 제공하기 위하여 페이징 정보와 게시글 정보를 뷰로 매핑함.
```java
@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final BoardContentService boardContentService;
    private final SampleCsvParserService sampleCsvParserService;
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
}
```
- boardPage.content가 이터레이션 하며 각 td에 할당
- pageInfo.previousPaging이 존재 한다면 첫페이지 할당
- pageInfo.nextPaging 존재 한다면 끝페이지 할당
- i: ${#numbers.sequence(pageInfo.startPageNum, pageInfo.lastPageNum)} 가 순회하며 번호 할당
- th:href="@{/board(page=${i}+1)}" 클릭하였을때 해당페이지로 이동 링크
```html
<table>
        <thead>
        <tr>
            <th>번호</th>
            <th>제목</th>
            <th>글쓴이</th>
            <th>작성일자</th>
        </tr>
        </thead>
        <tbody>
        <tr th:each="board : ${boardPage.content}">
            <td th:text="${board.id}"></td>
            <td class="center aligned">
                <a th:href="@{'/board/'+${board.id}}" th:text="${board.title}"></a>
            </td>
            <td th:text="${board.user.nickName}"></td>
            <td th:text="${board.registDt}"></td>
        </tr>
        </tbody>
    </table>
    <div>
        <a th:href="@{/edit}" class="">글쓰기</a>
    </div>

    <div class="">
        <th:block th:if="${pageInfo.previousPaging != null}">
            <div>
                <a th:href="@{/board(page=1)}">첫페이지</a>
            </div>
            <div>
                <a th:href="@{/board(page=${pageInfo.previousPaging} + 1)}"><i class="angle double left icon"></i></a>
            </div>
        </th:block>

        <th:block th:each="i: ${#numbers.sequence(pageInfo.startPageNum, pageInfo.lastPageNum)}">
            <div th:if="${boardPage.number == i}">[[${i}+1]]</div>
            <div th:unless="${boardPage.number == i}">
                <a th:href="@{/board(page=${i}+1)}">[[${i}+1]]</a>
            </div>
        </th:block>
        <th:block th:if="${pageInfo.nextPaging != null}">
            <div>
                <a th:href="@{/board(page=${pageInfo.nextPaging} + 1)}"></a>
            </div>
            <div>
                <a th:href="@{/board(page=${boardPage.totalPages})}">끝페이지</a>
            </div>
        </th:block>
    </div>
```