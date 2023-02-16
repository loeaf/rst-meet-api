package com.loeaf.rstmeet.service;

import com.loeaf.common.misc.Service;
import com.loeaf.rstmeet.model.Restaurant;
import com.loeaf.rstmeet.model.TasteRoom;

import java.util.List;

public interface TasteRoomService extends Service<TasteRoom, String> {
    List<TasteRoom> findByRestaurant(Restaurant restaurant);
}
