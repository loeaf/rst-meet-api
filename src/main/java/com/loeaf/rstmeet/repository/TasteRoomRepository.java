package com.loeaf.rstmeet.repository;

import com.loeaf.rstmeet.model.Restaurant;
import com.loeaf.rstmeet.model.TasteRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasteRoomRepository extends JpaRepository<TasteRoom, String> {
    List<TasteRoom> findByRestaurantId(Restaurant restaurant);
}