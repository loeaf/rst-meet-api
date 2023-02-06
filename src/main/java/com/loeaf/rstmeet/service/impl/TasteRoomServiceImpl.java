package com.loeaf.rstmeet.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.rstmeet.model.TasteRoom;
import com.loeaf.rstmeet.repository.TasteRoomRepository;
import com.loeaf.rstmeet.service.TasteRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class TasteRoomServiceImpl
        extends ServiceImpl<TasteRoomRepository, TasteRoom, String>
        implements TasteRoomService {
    private final TasteRoomRepository jpaRepo;

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new TasteRoom());
    }
}
