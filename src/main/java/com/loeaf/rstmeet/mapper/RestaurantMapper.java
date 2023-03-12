package com.loeaf.rstmeet.mapper;

import com.loeaf.rstmeet.dto.params.RestaurantParam;
import com.loeaf.rstmeet.model.RestaurantDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RestaurantMapper {
    List<RestaurantDto> findRestaurant(RestaurantParam o);

    List<RestaurantDto> findNearestRestaurant(RestaurantParam o);
}