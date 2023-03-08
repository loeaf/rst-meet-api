package com.loeaf.rstmeet.repository;

import com.loeaf.rstmeet.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
    Restaurant findByRestaurantNumber(Integer restaurantId);
    List<Restaurant> findByRoadAddressAndName(String roadAddress, String name);
}