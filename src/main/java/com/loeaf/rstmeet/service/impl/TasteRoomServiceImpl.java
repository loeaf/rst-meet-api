package com.loeaf.rstmeet.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.rstmeet.model.Restaurant;
import com.loeaf.rstmeet.model.TasteRoom;
import com.loeaf.rstmeet.repository.TasteRoomRepository;
import com.loeaf.rstmeet.service.TasteRoomService;
import com.loeaf.siginin.dto.UserToken;
import com.loeaf.siginin.model.User;
import com.loeaf.siginin.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TasteRoomServiceImpl
        extends ServiceImpl<TasteRoomRepository, com.loeaf.rstmeet.model.TasteRoom, String>
        implements TasteRoomService {
    private final TasteRoomRepository jpaRepo;
    @Autowired
    UserToken userToken;
    @Autowired
    AccountService accountService;

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new com.loeaf.rstmeet.model.TasteRoom());
    }

    @Override
    public List<com.loeaf.rstmeet.model.TasteRoom> findByRestaurant(Restaurant restaurant) {
        System.out.println(userToken.getUser().toString());
        User user = userToken.findUserByDb();
        List<com.loeaf.rstmeet.model.TasteRoom> tasteRooms = this.jpaRepo.findByRestaurantAndUserNot(restaurant, user);
        return tasteRooms;
    }

    @Override
    public List<TasteRoom> selectTasteRoom(String restaurantId) {
        String userId = userToken.getUser().getId();
        List<TasteRoom> result = jpaRepo.findTasteRoom(restaurantId, userId);
        return result;
    }
}
