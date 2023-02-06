package com.loeaf.rstmeet.service;

import com.loeaf.common.misc.Service;
import com.loeaf.rstmeet.model.CmmnCode;

import java.util.List;

public interface CmmnCodeService extends Service<CmmnCode, String> {
    List<CmmnCode> findCity();
}
