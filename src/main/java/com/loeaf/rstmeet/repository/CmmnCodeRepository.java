package com.loeaf.rstmeet.repository;

import com.loeaf.rstmeet.model.CmmnCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CmmnCodeRepository extends JpaRepository<CmmnCode, String> {
    List<CmmnCode> findCmmnCodeByParentCode(CmmnCode cd);

    CmmnCode findByCodeName(String menuType);
}