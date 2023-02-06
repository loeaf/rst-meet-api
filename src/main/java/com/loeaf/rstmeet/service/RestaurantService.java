package com.loeaf.rstmeet.service;

import com.loeaf.common.misc.Service;
import com.loeaf.rstmeet.model.Restaurant;

import java.io.FileNotFoundException;

public interface RestaurantService extends Service<Restaurant, String> {
    void registBulkByCSV() throws FileNotFoundException;
}
