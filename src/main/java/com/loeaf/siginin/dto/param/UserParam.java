package com.loeaf.siginin.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserParam {
    private String id;
    private String loginId;
    private String password;
    private String accountType;
    private String nickName;
}