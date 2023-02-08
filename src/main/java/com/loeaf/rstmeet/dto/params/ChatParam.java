package com.loeaf.rstmeet.dto.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatParam {
    private String content;
    private String tasteRoomId;
    private String userId;
}