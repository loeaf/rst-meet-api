package com.loeaf.rstmeet.dto.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TasteRoomMemberParam {
    private String id;
    private String tasteRoomId;
    private String accountId;
    private String createDate;
}