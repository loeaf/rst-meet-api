package com.loeaf.rstmeet.mapper;

import com.loeaf.rstmeet.model.Restaurant;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    public List<Restaurant> findRestaurant(@Param("restaurantId") String restaurantId);
}