package com.nexign.testNexign.model;

public enum CallType {
    OUTGOING("01"),
    INCOMING("02");

    private CallType(String code){
        this.code = code;
    }
    private String code;

    public String getCode() {
        return code;
    }
}
