package com.baoshine.questionnaire.enums;

public enum BusinessType {

    POLICY(1L, "保单");

    private Long key;

    private String desc;

    public Long getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    BusinessType(Long key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
