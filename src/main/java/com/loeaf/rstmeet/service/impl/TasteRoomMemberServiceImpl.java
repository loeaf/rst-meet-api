package com.loeaf.rstmeet.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.rstmeet.model.TasteRoomMember;
import com.loeaf.rstmeet.repository.TasteRoomMemberRepository;
import com.loeaf.rstmeet.service.TasteRoomMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class TasteRoomMemberServiceImpl
        extends ServiceImpl<TasteRoomMemberRepository, TasteRoomMember, String>
        implements TasteRoomMemberService {
    private final TasteRoomMemberRepository jpaRepo;

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new TasteRoomMember());
    }
}
