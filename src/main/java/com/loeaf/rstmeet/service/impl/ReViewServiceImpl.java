package com.loeaf.rstmeet.service.impl;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.rstmeet.model.ReView;
import com.loeaf.rstmeet.repository.ReViewRepository;
import com.loeaf.rstmeet.service.ReViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class ReViewServiceImpl
        extends ServiceImpl<ReViewRepository, ReView, String>
        implements ReViewService {
    private final ReViewRepository jpaRepo;

    @PostConstruct
    private void init() {
        super.set(jpaRepo, new ReView());
    }
}
