package com.baoshine.questionnaire.enums;

public enum QuestionnaireStatus {

    DRAFT(1L, "草稿"),

    EFFECTIVE(2L, "生效");

    private Long key;

    private String desc;

    QuestionnaireStatus(Long key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Long getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
