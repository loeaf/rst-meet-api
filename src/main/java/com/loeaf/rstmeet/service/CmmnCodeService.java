package com.loeaf.rstmeet.service;

import com.loeaf.common.misc.Service;
import com.loeaf.rstmeet.model.CmmnCode;
import com.loeaf.rstmeet.type.CountryType;

import java.util.List;

public interface CmmnCodeService extends Service<CmmnCode, String> {
    List<CmmnCode> findCity(CountryType countryType);
    List<CmmnCode> findNation();
}
