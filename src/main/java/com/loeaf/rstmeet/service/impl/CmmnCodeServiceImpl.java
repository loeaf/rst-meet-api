package com.loeaf.rstmeet.service.impl;

import com.loeaf.rstmeet.model.CmmnCode;
import com.loeaf.rstmeet.repository.CmmnCodeRepository;
import com.loeaf.rstmeet.service.CmmnCodeService;

import com.loeaf.common.misc.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CmmnCodeServiceImpl
        extends ServiceImpl<CmmnCodeRepository, CmmnCode, String>
        implements CmmnCodeService {
private final CmmnCodeRepository jpaRepo;

@PostConstruct
private void init(){
        super.set(jpaRepo,new CmmnCode());
        }
}
