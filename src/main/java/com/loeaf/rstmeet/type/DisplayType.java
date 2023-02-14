package com.loeaf.rstmeet.type;

public enum DisplayType {
    대표("Y"),
    비대표("N");
    private String name;

    DisplayType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}