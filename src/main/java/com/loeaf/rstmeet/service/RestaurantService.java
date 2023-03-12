package com.loeaf.rstmeet.service;

import com.loeaf.common.misc.Service;
import com.loeaf.rstmeet.dto.params.RestaurantParam;
import com.loeaf.rstmeet.model.Restaurant;
import com.loeaf.rstmeet.model.RestaurantDto;

import java.io.FileNotFoundException;
import java.util.List;

public interface RestaurantService extends Service<Restaurant, String> {
    void registBulkByCSV() throws FileNotFoundException;

    List<RestaurantDto> findRestaurant(RestaurantParam restaurant);

    List<Restaurant> findRestaurantByRoadAddress(String roadAddress, String name);

    List<RestaurantDto> findNearestRestaurant(RestaurantParam o);
}
