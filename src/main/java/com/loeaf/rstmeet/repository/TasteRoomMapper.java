package com.loeaf.rstmeet.repository;

import com.loeaf.common.conn.TasteRoomConnMapper;
import com.loeaf.rstmeet.model.TasteRoom;

import java.util.List;

@TasteRoomConnMapper
public interface TasteRoomMapper {
    public List<TasteRoom> findTasteRoom(String restaurantId, String userId);
}