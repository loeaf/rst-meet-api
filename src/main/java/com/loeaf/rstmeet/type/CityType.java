package com.loeaf.rstmeet.type;

public enum CityType {
    세종("SEJONG"),
    서울("SEOUL"),
    부산("BUSAN"),
    광주("GWANGJU"),
    대전("DAEJEON"),
    교토("KYOTO"),
    오사카("OSAKA"),
    후쿠오카("FUKUOKA");
    private String name;

    CityType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
