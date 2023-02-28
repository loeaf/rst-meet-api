package com.loeaf.rstmeet.service.impl;

import com.loeaf.rstmeet.model.CmmnCode;
import com.loeaf.rstmeet.repository.CmmnCodeRepository;
import com.loeaf.rstmeet.service.CmmnCodeService;

import com.loeaf.common.misc.ServiceImpl;
import com.loeaf.rstmeet.type.CountryType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.util.List;

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

    @Override
    public List<CmmnCode> findCity(CountryType countryType) {
        var cd = new CmmnCode();
        cd.setId(countryType.getName());
        return jpaRepo.findCmmnCodeByParentCode(cd);
    }

    @Override
    public List<CmmnCode> findNation() {
    var cd = new CmmnCode();
    cd.setId("NATION");
    return jpaRepo.findCmmnCodeByParentCode(cd);
    }
}
