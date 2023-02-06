package com.loeaf.rstmeet.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.rstmeet.model.Restaurant;
import com.loeaf.rstmeet.repository.RestaurantRepository;
import com.loeaf.rstmeet.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl
        extends ServiceImpl<RestaurantRepository, Restaurant, String>
        implements RestaurantService {
    private final RestaurantRepository jpaRepo;

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new Restaurant());
    }
}
