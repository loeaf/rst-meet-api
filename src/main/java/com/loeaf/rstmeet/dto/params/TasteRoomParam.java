package com.loeaf.rstmeet.dto.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TasteRoomParam {
    String restaurantId;
    String content;
}