package com.loeaf.rstmeet.type;

public enum FoodType {
    한식("KOREAN"),
    중식("CHINESE"),
    일식("JAPANESE"),
    양식("WESTERN"),
    아시안식("ASIANFOOD"),
    회식("GETTOGETHER"),
    기타("ETC");
    private String name;

    FoodType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
