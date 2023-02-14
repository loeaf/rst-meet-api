package com.loeaf.rstmeet.controller;

import com.loeaf.rstmeet.model.CmmnCode;
import com.loeaf.rstmeet.service.CmmnCodeService;
import com.loeaf.rstmeet.service.MenuService;
import com.loeaf.rstmeet.service.ReViewService;
import com.loeaf.rstmeet.service.RestaurantService;
import com.loeaf.rstmeet.type.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.beans.Transient;

@RestController
@RequestMapping("/DefaultData")
@Api(value = "DefaultData")
public class DefaultDataRestController {

    private RestaurantService restaurantService;
    private ReViewService reViewService;
    private MenuService menuService;
    private CmmnCodeService cmmnCodeService;

    public DefaultDataRestController(RestaurantService service,
                                     ReViewService reViewService,
                                     MenuService menuService,
                                     CmmnCodeService cmmnCodeService
    ) {
        this.restaurantService = service;
        this.reViewService = reViewService;
        this.menuService = menuService;
        this.cmmnCodeService = cmmnCodeService;
    }
    // NATION POST
    @GetMapping("/NATION")
    @ApiOperation(value = "국가코드 등록")
    public ResponseEntity<Object> registNation(HttpServletRequest request) throws Exception {
        CmmnCode cd = new CmmnCode();
        cd.setId("NATION");
        cd.setCodeName("NATION");
        cmmnCodeService.regist(cd);
        CmmnCode child1 = new CmmnCode();
        child1.setId(CountryType.일본.getName());
        child1.setParentCode(cmmnCodeService.findById("NATION"));
        child1.setCodeName(CountryType.일본.getName());
        cmmnCodeService.regist(child1);
        CmmnCode child2 = new CmmnCode();
        child2.setId(CountryType.한국.getName());
        child2.setParentCode(cmmnCodeService.findById("NATION"));
        child2.setCodeName(CountryType.한국.getName());
        cmmnCodeService.regist(child2);
        return null;
    }
    // NATION POST
    @GetMapping("/CITYTYPE")
    @ApiOperation(value = "도시코드 등록")
    @Transient
    public ResponseEntity<Object> registCity(HttpServletRequest request) throws Exception {
        CmmnCode child1 = new CmmnCode();
        child1.setId(CityType.부산.getName());
        child1.setParentCode(cmmnCodeService.findById(CountryType.한국.getName()));
        child1.setCodeName(CityType.부산.getName());
        cmmnCodeService.regist(child1);
        CmmnCode child2 = new CmmnCode();
        child2.setId(CityType.서울.getName());
        child2.setParentCode(cmmnCodeService.findById(CountryType.한국.getName()));
        child2.setCodeName(CityType.서울.getName());
        cmmnCodeService.regist(child2);
        CmmnCode child3 = new CmmnCode();
        child3.setId(CityType.교토.getName());
        child2.setParentCode(cmmnCodeService.findById(CountryType.일본.getName()));
        child3.setCodeName(CityType.교토.getName());
        cmmnCodeService.regist(child3);
        CmmnCode child4 = new CmmnCode();
        child4.setId(CityType.후쿠오카.getName());
        child4.setParentCode(cmmnCodeService.findById(CountryType.일본.getName()));
        child4.setCodeName(CityType.후쿠오카.getName());
        cmmnCodeService.regist(child4);
        CmmnCode child5 = new CmmnCode();
        child5.setId(CityType.오사카.getName());
        child5.setParentCode(cmmnCodeService.findById(CountryType.일본.getName()));
        child5.setCodeName(CityType.오사카.getName());
        cmmnCodeService.regist(child5);
        return null;
    }
    // FOODTYPE POST
    @GetMapping("/FOODTYPE")
    @ApiOperation(value = "음식종류 등록")
    public ResponseEntity<Object> registFoodType(HttpServletRequest request) throws Exception {
        CmmnCode cd = new CmmnCode();
        cd.setId("FOODTYPE");
        cd.setCodeName("FOODTYPE");
        cmmnCodeService.regist(cd);
        CmmnCode child1 = new CmmnCode();
        child1.setId(FoodType.한식.getName());
        child1.setParentCode(cmmnCodeService.findById("FOODTYPE"));
        child1.setCodeName(FoodType.한식.getName());
        cmmnCodeService.regist(child1);
        CmmnCode child2 = new CmmnCode();
        child2.setId(FoodType.일식.getName());
        child2.setParentCode(cmmnCodeService.findById("FOODTYPE"));
        child2.setCodeName(FoodType.일식.getName());
        cmmnCodeService.regist(child2);
        CmmnCode child3 = new CmmnCode();
        child3.setId(FoodType.중식.getName());
        child3.setParentCode(cmmnCodeService.findById("FOODTYPE"));
        child3.setCodeName(FoodType.중식.getName());
        cmmnCodeService.regist(child3);
        CmmnCode child4 = new CmmnCode();
        child4.setId(FoodType.양식.getName());
        child4.setParentCode(cmmnCodeService.findById("FOODTYPE"));
        child4.setCodeName(FoodType.양식.getName());
        cmmnCodeService.regist(child4);
        CmmnCode child5 = new CmmnCode();
        child5.setId(FoodType.아시안식.getName());
        child5.setParentCode(cmmnCodeService.findById("FOODTYPE"));
        child5.setCodeName(FoodType.아시안식.getName());
        cmmnCodeService.regist(child5);
        CmmnCode child6 = new CmmnCode();
        child6.setId(FoodType.회식.getName());
        child6.setParentCode(cmmnCodeService.findById("FOODTYPE"));
        child6.setCodeName(FoodType.회식.getName());
        cmmnCodeService.regist(child6);
        CmmnCode child7 = new CmmnCode();
        child7.setId(FoodType.기타.getName());
        child7.setParentCode(cmmnCodeService.findById("FOODTYPE"));
        child7.setCodeName(FoodType.기타.getName());
        cmmnCodeService.regist(child7);
        return null;
    }
    // Review Post
    @GetMapping("/MenuType")
    @ApiOperation(value = "음식종류 등록")
    public ResponseEntity<Object> registMenuType(HttpServletRequest request) throws Exception {
        CmmnCode cd = new CmmnCode();
        cd.setId("MENUTYPE");
        cd.setCodeName("MENUTYPE");
        cmmnCodeService.regist(cd);
        CmmnCode child1 = new CmmnCode();
        child1.setId(MenuType.식사.getName());
        child1.setParentCode(cmmnCodeService.findById("MENUTYPE"));
        child1.setCodeName(MenuType.식사.getName());
        cmmnCodeService.regist(child1);
        CmmnCode child2 = new CmmnCode();
        child2.setId(MenuType.안주.getName());
        child2.setParentCode(cmmnCodeService.findById("MENUTYPE"));
        child2.setCodeName(MenuType.안주.getName());
        cmmnCodeService.regist(child2);
        return null;
    }
    // Review Post
    @GetMapping("/DISPLAYTYPE")
    @ApiOperation(value = "가시화타입 등록")
    public ResponseEntity<Object> registDesplayType(HttpServletRequest request) throws Exception {
        CmmnCode cd = new CmmnCode();
        cd.setId("DESPALYTYPE");
        cd.setCodeName("DESPALYTYPE");
        cmmnCodeService.regist(cd);
        CmmnCode child1 = new CmmnCode();
        child1.setId(DisplayType.대표.getName());
        child1.setParentCode(cmmnCodeService.findById("DESPALYTYPE"));
        child1.setCodeName(DisplayType.대표.getName());
        cmmnCodeService.regist(child1);
        CmmnCode child2 = new CmmnCode();
        child2.setId(DisplayType.비대표.getName());
        child2.setParentCode(cmmnCodeService.findById("DESPALYTYPE"));
        child2.setCodeName(DisplayType.비대표.getName());
        cmmnCodeService.regist(child2);
        return null;
    }

    @GetMapping("/restaurantBulkInsert")
    @ApiOperation(value = "등록")
    public ResponseEntity<Object> restaurantBulkInsert(HttpServletRequest request) throws Exception {
        this.restaurantService.registBulkByCSV();
        return null;
    }

    @GetMapping("/reviewBulkInsert")
    @ApiOperation(value = "등록")
    public ResponseEntity<Object> reviewBulkInsert(HttpServletRequest request) throws Exception {
        this.reViewService.registBulkByCSV();
        return null;
    }

    @GetMapping("/menuBulkInsert")
    @ApiOperation(value = "등록")
    public ResponseEntity<Object> menuBulkInsert(HttpServletRequest request) throws Exception {
        this.menuService.registBulkByCSV();
        return null;
    }
}