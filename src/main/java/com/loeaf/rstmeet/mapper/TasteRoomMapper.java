package com.loeaf.rstmeet.mapper;

import com.loeaf.rstmeet.model.TasteRoom;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TasteRoomMapper {
    public List<TasteRoom> findTasteRoom(String restaurantId, String userId);
    /**
     * 로그인한 대상자가 레스토랑을 통해 남아 있는 챗팅방 조회 API
     * @param restaurantId
     * @param id
     * @return
     */
    List<TasteRoom> findTasteRoomByRestAndNotMe(@Param("restaurantId") String restaurantId,
                                                @Param("userId") String userId);
    /**
     * 로그인한 대상자가 자신이 들어간 챗팅방 조회 API
     * @param id
     * @return
     */
    List<TasteRoom> findTasteRoomByMe(@Param("userId") String userId);

}