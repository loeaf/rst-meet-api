package com.loeaf.rstmeet.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.rstmeet.model.Chatting;
import com.loeaf.rstmeet.repository.ChattingRepository;
import com.loeaf.rstmeet.service.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class ChattingServiceImpl
        extends ServiceImpl<ChattingRepository, Chatting, String>
        implements ChattingService {
    private final ChattingRepository jpaRepo;

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new Chatting());
    }
}
