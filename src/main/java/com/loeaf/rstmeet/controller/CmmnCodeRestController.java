package com.loeaf.rstmeet.controller;

import com.loeaf.rstmeet.model.CmmnCode;
import com.loeaf.rstmeet.service.CmmnCodeService;
import com.loeaf.rstmeet.type.CityType;
import com.loeaf.rstmeet.type.CountryType;
import com.loeaf.rstmeet.type.FoodType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.beans.Transient;

@RestController
@RequestMapping("/CmmnCode")
@Api(value = "CmmnCode")
public class CmmnCodeRestController {

    private CmmnCodeService service;

    public CmmnCodeRestController(CmmnCodeService service) {
        this.service = service;
    }


    // find city by service
    @GetMapping("/getCity")
    @ApiOperation(value = "도시 목록")
    public ResponseEntity<Object> findCity(HttpServletRequest request) throws Exception {
        return ResponseEntity.ok(service.findCity());
    }
    // NATION POST
    @GetMapping("/NATION")
    @ApiOperation(value = "국가코드 등록")
    public ResponseEntity<Object> registNation(HttpServletRequest request) throws Exception {
        CmmnCode cd = new CmmnCode();
        cd.setId("NATION");
        cd.setCodeName("NATION");
        service.regist(cd);
        CmmnCode child1 = new CmmnCode();
        child1.setId(CountryType.일본.getName());
        child1.setParentCode(service.findById("NATION"));
        child1.setCodeName(CountryType.일본.getName());
        service.regist(child1);
        CmmnCode child2 = new CmmnCode();
        child2.setId(CountryType.한국.getName());
        child2.setParentCode(service.findById("NATION"));
        child2.setCodeName(CountryType.한국.getName());
        service.regist(child2);
        return null;
    }
    // NATION POST
    @GetMapping("/CITY")
    @ApiOperation(value = "도시코드 등록")
    @Transient
    public ResponseEntity<Object> registCity(HttpServletRequest request) throws Exception {
        CmmnCode child1 = new CmmnCode();
        child1.setId(CityType.부산.getName());
        child1.setParentCode(service.findById(CountryType.한국.getName()));
        child1.setCodeName(CityType.부산.getName());
        service.regist(child1);
        CmmnCode child2 = new CmmnCode();
        child2.setId(CityType.서울.getName());
        child2.setParentCode(service.findById(CountryType.한국.getName()));
        child2.setCodeName(CityType.서울.getName());
        service.regist(child2);
        CmmnCode child3 = new CmmnCode();
        child3.setId(CityType.교토.getName());
        child2.setParentCode(service.findById(CountryType.일본.getName()));
        child3.setCodeName(CityType.교토.getName());
        service.regist(child3);
        CmmnCode child4 = new CmmnCode();
        child4.setId(CityType.후쿠오카.getName());
        child4.setParentCode(service.findById(CountryType.일본.getName()));
        child4.setCodeName(CityType.후쿠오카.getName());
        service.regist(child4);
        CmmnCode child5 = new CmmnCode();
        child5.setId(CityType.오사카.getName());
        child5.setParentCode(service.findById(CountryType.일본.getName()));
        child5.setCodeName(CityType.오사카.getName());
        service.regist(child5);
        return null;
    }
    // FOODTYPE POST
    @GetMapping("/FOODTYPE")
    @ApiOperation(value = "음식종류 등록")
    public ResponseEntity<Object> registFoodType(HttpServletRequest request) throws Exception {
        CmmnCode cd = new CmmnCode();
        cd.setId("FOODTYPE");
        cd.setCodeName("FOODTYPE");
        service.regist(cd);
        CmmnCode child1 = new CmmnCode();
        child1.setId(FoodType.한식.getName());
        child1.setParentCode(service.findById("FOODTYPE"));
        child1.setCodeName(FoodType.한식.getName());
        service.regist(child1);
        CmmnCode child2 = new CmmnCode();
        child2.setId(FoodType.일식.getName());
        child2.setParentCode(service.findById("FOODTYPE"));
        child2.setCodeName(FoodType.일식.getName());
        service.regist(child2);
        CmmnCode child3 = new CmmnCode();
        child3.setId(FoodType.중식.getName());
        child3.setParentCode(service.findById("FOODTYPE"));
        child3.setCodeName(FoodType.중식.getName());
        service.regist(child3);
        CmmnCode child4 = new CmmnCode();
        child4.setId(FoodType.양식.getName());
        child4.setParentCode(service.findById("FOODTYPE"));
        child4.setCodeName(FoodType.양식.getName());
        service.regist(child4);
        CmmnCode child5 = new CmmnCode();
        child5.setId(FoodType.아시안식.getName());
        child5.setParentCode(service.findById("FOODTYPE"));
        child5.setCodeName(FoodType.아시안식.getName());
        service.regist(child5);
        CmmnCode child6 = new CmmnCode();
        child6.setId(FoodType.회식.getName());
        child6.setParentCode(service.findById("FOODTYPE"));
        child6.setCodeName(FoodType.회식.getName());
        service.regist(child6);
        CmmnCode child7 = new CmmnCode();
        child7.setId(FoodType.기타.getName());
        child7.setParentCode(service.findById("FOODTYPE"));
        child7.setCodeName(FoodType.기타.getName());
        service.regist(child7);
        return null;
    }
}