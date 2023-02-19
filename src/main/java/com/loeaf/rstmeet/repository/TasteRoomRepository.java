package com.loeaf.rstmeet.repository;

import com.loeaf.rstmeet.model.Restaurant;
import com.loeaf.rstmeet.model.TasteRoom;
import com.loeaf.siginin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TasteRoomRepository extends JpaRepository<com.loeaf.rstmeet.model.TasteRoom, String> {
    List<com.loeaf.rstmeet.model.TasteRoom> findByRestaurantAndUserNot(Restaurant restaurant, User user);
    @Query(value =
                "select A.* from taste_room A, taste_room_member B \n" +
                "        where A.id = B.taste_room_id \n" +
                        "  and B.user_id != :id \n" +
                        "  and A.restaurant_id = :restaurantId ",
                        nativeQuery = true)
    List<TasteRoom> findTasteRoom(String restaurantId, String id);
}