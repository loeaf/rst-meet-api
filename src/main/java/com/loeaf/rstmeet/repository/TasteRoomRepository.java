package com.loeaf.rstmeet.repository;

import com.loeaf.rstmeet.model.Restaurant;
import com.loeaf.siginin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasteRoomRepository extends JpaRepository<com.loeaf.rstmeet.model.TasteRoom, String> {
    List<com.loeaf.rstmeet.model.TasteRoom> findByRestaurantAndUserNot(Restaurant restaurant, User user);
}