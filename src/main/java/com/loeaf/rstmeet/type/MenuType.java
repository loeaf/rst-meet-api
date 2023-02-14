package com.loeaf.rstmeet.type;

public enum MenuType {
    식사("MAIN"),
    안주("SIDE");
    private String name;

    MenuType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}