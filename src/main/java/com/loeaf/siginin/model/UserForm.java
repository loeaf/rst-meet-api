package com.loeaf.siginin.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserForm {
    @Email(message = "이메일 형식을 입력해주세요.")
    private String email;
    @NotBlank(message = "패스워드의 길이는 3글자 이상 입력해주세요.")
    @Length(min = 3, max = 20, message = "패스워드의 길이는 3글자 이상 입력해주세요.")
    private String password;
    @NotBlank(message = "닉네임의 길이는 1글자 이상, 16글자 이하로 입력해주세요.")
    @Length(min = 1, max = 16, message = "닉네임의 길이는 1글자 이상, 16글자 이하로 입력해주세요.")
    private String nickName;
}
