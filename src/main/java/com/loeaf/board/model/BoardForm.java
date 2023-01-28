package com.loeaf.board.model;

import com.loeaf.file.domain.MultipartFileMaster;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardForm extends MultipartFileMaster {
    private Long id;
    @NotBlank(message = "제목의 길이는 1글자 이상, 100글자 이하로 입력해주세요.")
    @Length(min = 1, max = 100, message = "제목의 길이는 1글자 이상, 100글자 이하로 입력해주세요.")
    private String title;
    @NotBlank(message = "내용의 길이는 1글자 이상, 2000글자 이하로 입력해주세요.")
    @Length(min = 1, max = 2000, message = "내용의 길이는 1글자 이상, 2000글자 이하로 입력해주세요.")
    private String content;


    public BoardForm(List<MultipartFile> files, Long id,
                     @NotBlank(message = "제목의 길이는 1글자 이상, 100글자 이하로 입력해주세요.")
                     @Length(min = 1, max = 100, message = "제목의 길이는 1글자 이상, 100글자 이하로 입력해주세요.") String title,
                     @NotBlank(message = "내용의 길이는 1글자 이상, 2000글자 이하로 입력해주세요.")
                     @Length(min = 1, max = 2000, message = "내용의 길이는 1글자 이상, 2000글자 이하로 입력해주세요.") String content) {
        super(files);
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public BoardForm(List<MultipartFile> files,
                     @NotBlank(message = "제목의 길이는 1글자 이상, 100글자 이하로 입력해주세요.")
                     @Length(min = 1, max = 100, message = "제목의 길이는 1글자 이상, 100글자 이하로 입력해주세요.") String title,
                     @NotBlank(message = "내용의 길이는 1글자 이상, 2000글자 이하로 입력해주세요.")
                     @Length(min = 1, max = 2000, message = "내용의 길이는 1글자 이상, 2000글자 이하로 입력해주세요.") String content) {
        super(files);
        this.title = title;
        this.content = content;
    }
}
