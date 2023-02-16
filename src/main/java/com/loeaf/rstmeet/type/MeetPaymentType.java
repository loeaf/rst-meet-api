package com.loeaf.rstmeet.type;

public enum MeetPaymentType {
    더치페이("DUTCH"),
    미정("UNDECIDED"),
    공짜("FREE");
    private String name;

    MeetPaymentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
