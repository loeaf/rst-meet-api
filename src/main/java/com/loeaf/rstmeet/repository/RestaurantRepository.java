package com.loeaf.rstmeet.repository;

import com.loeaf.rstmeet.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, String> {
}